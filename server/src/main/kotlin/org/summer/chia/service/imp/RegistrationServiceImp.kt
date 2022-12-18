package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.interceptor.TransactionAspectSupport
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CspInfoMapper
import org.summer.chia.mapper.MessageMapper
import org.summer.chia.mapper.RegistrationMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.RegistrationListItem
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.StudentListItem
import org.summer.chia.pojo.dto.CspInfo
import org.summer.chia.pojo.dto.Message
import org.summer.chia.pojo.dto.Registration
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.RegistrationService
import org.summer.chia.utils.Log
import org.summer.chia.utils.MailSendUtil
import java.sql.Timestamp

@Service
@EnableAspectJAutoProxy(exposeProxy = true)
class RegistrationServiceImp : ServiceImpl<RegistrationMapper, Registration>(), RegistrationService {

    @Autowired
    private lateinit var studentMapper: StudentMapper

    @Autowired
    private lateinit var cspInfoMapper: CspInfoMapper

    @Autowired
    private lateinit var mailSendUtil: MailSendUtil

    @Autowired
    private lateinit var messageMapper: MessageMapper

    @Transactional
    override fun registrationList(objList: List<RegistrationListItem>): Result {
        var transactionalPoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint()
        val studentId = studentMapper.selectList(
            KtQueryWrapper(Student::class.java).`in`(
                Student::studentNumber,
                objList.map { it.studentIdNumber })
        ).associateBy { it.studentNumber }
        val errors = ArrayList<String>()
        try {
            objList.forEach {
                studentId[it.studentIdNumber]?.let { p ->
                    val row =
                        baseMapper.insertRegistrationInfo(Registration(null, it.cspId, p.id!!, null, it.type, null))
                    if (row != 0) {
                        if (it.type == 1 && p.freeTimes > 0) {
                            studentMapper.update(
                                null,
                                KtUpdateWrapper(Student::class.java).eq(Student::id, p.id!!).set(Student::freeTimes, 0)
                            )
                        }
                        if (it.type == 1 && p.freeTimes == 0) {
                            errors.add(p.name)
                            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(transactionalPoint)
                            return@let
                        }
                    }
                    transactionalPoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint()
                }
            }
            val cspId = objList.map { it.cspId }.toSet()
            cspId.forEach {
                cspInfoMapper.update(
                    null,
                    KtUpdateWrapper(CspInfo::class.java).eq(CspInfo::id, it).set(CspInfo::stage, 1)
                )
            }
        } catch (e: Exception) {
            Log.error(this.javaClass, this::registrationList.name + "-> Insert Exception: " + e.message, e.stackTrace)
            throw SqlException("Insert Exception", this::registrationList.name)
        }
        if (errors.isEmpty()) {
            return Result.success()
        } else {
            val messasge = StringBuilder()
            errors.forEach {
                messasge.append(it).append(",")
            }
            messasge.replace(messasge.lastIndex, messasge.length, "这(几)位同学免费次数已用尽，却报名免费团")
            return Result.error(messasge.toString())
        }

    }

    @Transactional
    override fun transcriptsList(objList: List<RegistrationListItem>): Result {
        val studentId = studentMapper.selectList(
            KtQueryWrapper(Student::class.java).`in`(
                Student::studentNumber,
                objList.map { it.studentIdNumber })
        ).associateBy { it.studentNumber }
        try {
            val cspId = objList.map { it.cspId }.toSet()
            val cspInfo = cspInfoMapper.selectList(KtQueryWrapper(CspInfo::class.java).`in`(CspInfo::id, cspId))
                .associate { it.id to it.freeThreshold }
            cspId.forEach {
                cspInfoMapper.update(
                    null,
                    KtUpdateWrapper(CspInfo::class.java).eq(CspInfo::id, it).set(CspInfo::stage, 2)
                )
            }
            objList.forEach {
                studentId[it.studentIdNumber]?.let { p ->
                    if (it.socre!! >= cspInfo[it.cspId]!!)
                        studentMapper.update(
                            null,
                            KtUpdateWrapper(Student::class.java).eq(Student::id, p.id!!).set(Student::freeTimes, 1)
                        )
                    baseMapper.update(
                        null,
                        KtUpdateWrapper(Registration::class.java).eq(Registration::cspId, it.cspId)
                            .eq(Registration::studentId, p.id!!)
                            .set(Registration::miss, 0)
                            .set(Registration::score, it.socre!!)
                    )
                }
            }
        } catch (e: Exception) {
            Log.error(this.javaClass, this::transcriptsList.name + "-> Update Exception: " + e.message, e.stackTrace)
            throw SqlException("Update Exception", this::transcriptsList.name)
        }
        return Result.success()
    }

    override fun doQueryAbsentOfficialRegistration(cid: String, pageNum: String, pageSize: String): Result {
        val cspStage = cspInfoMapper.selectOne(KtQueryWrapper(CspInfo::class.java).eq(CspInfo::id, cid)).stage!!
        if (cspStage < 1) {
            return Result.error("正式报名名单未导入")
        }
        return try {
            val page = Page<StudentListItem>(pageNum.toLong(), pageSize.toLong())
            val students = baseMapper.absentOfficialRegistration(page, cid)
            val size = baseMapper.absentOfficialRegistrationNumber(cid)
            students.records.forEach { it.totalSize = size }
            Result.success(students.records)
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::doQueryAbsentOfficialRegistration.name + "-> Query Exception: " + e.message,
                e.stackTrace
            )
            throw SqlException("Query Exception", this::doQueryAbsentOfficialRegistration.name)
        }
    }

    override fun doQueryAbsentExam(cid: String, pageNum: String, pageSize: String): Result {
        val cspStage = cspInfoMapper.selectOne(KtQueryWrapper(CspInfo::class.java).eq(CspInfo::id, cid)).stage!!
        if (cspStage < 2) {
            return Result.error("成绩单未导入")
        }
        return try {
            val page = Page<StudentListItem>(pageNum.toLong(), pageSize.toLong())
            val students = baseMapper.absentExam(page, cid)
            val size = baseMapper.absentExamNumber(cid)
            students.records.forEach { it.totalSize = size }
            Result.success(students.records)
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::doQueryAbsentOfficialRegistration.name + "-> Query Exception: " + e.message,
                e.stackTrace
            )
            throw SqlException("Query Exception", this::doQueryAbsentOfficialRegistration.name)
        }
    }

    @Async
    override fun noticeStudent(cid: String, user: UserDetails): Result {
        val uid = (user as UserDetailsAdapter).getPayLoad().id!!
        val csp = cspInfoMapper.selectOne(KtQueryWrapper(CspInfo::class.java).eq(CspInfo::id, cid))
        val data = mapOf("cspName" to csp.name)
        var successNumber = 0
        val list = (doQueryAbsentOfficialRegistration(cid, "0", "1000").data as List<StudentListItem>).map { it.id }
        val errorStudentName = ArrayList<String>()
        return try {
            list.forEach {
                val student = studentMapper.selectOne(KtQueryWrapper(Student::class.java).eq(Student::id, it))
                try {
                    mailSendUtil.sendTemplateMail(student.email!!, "正式报名提醒", "notice", data)
                    successNumber += 1
                } catch (e: Exception) {
                    errorStudentName.add(student.name)
                }
            }
            if (errorStudentName.isNotEmpty()) {
                messageMapper.insert(
                    Message(
                        null,
                        Timestamp(System.currentTimeMillis()),
                        arrayToString(errorStudentName) + " 同学的邮件未发送成功",
                        "System",
                        uid,
                        null
                    )
                )
            } else {
                messageMapper.insert(
                    Message(
                        null,
                        Timestamp(System.currentTimeMillis()),
                        "所有邮件都已成功发送",
                        "System",
                        uid,
                        null
                    )
                )
            }
            Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::noticeStudent.name + "->" + e.message, e.stackTrace)
            Result.error("邮件发送失败")
        }
    }

    override fun queryWrongType(cid: String, pageNum: String, pageSize: String): Result {
        try {
            val page = Page<StudentListItem>(pageNum.toLong(), pageSize.toLong())
            val res = baseMapper.queryWrongType(page, cid)
            val total = baseMapper.queryWrongTypeNumber(cid)
            res.records.forEach {
                it.totalSize = total
            }
            return Result.success(res.records)
        } catch (e: Exception) {
            Log.error(javaClass, this::queryWrongType.name + "-> Query Exception: " + e.message, e.stackTrace)
            throw SqlException("Query Exception", this::queryWrongType.name)
        }
    }

    override fun queryOfficialList(cid: String, pageNum: String, pageSize: String): Result {
        try {
            val page = Page<StudentListItem>(pageNum.toLong(), pageSize.toLong())
            val res = baseMapper.queryOfficialList(page, cid)
            val total = baseMapper.queryOfficialNumber(cid)
            res.records.forEach {
                it.totalSize = total
            }
            return Result.success(res.records)
        } catch (e: Exception) {
            Log.error(javaClass, this::queryOfficialList.name + "-> Query Exception: " + e.message, e.stackTrace)
            throw SqlException("Query Exception", this::queryOfficialList.name)
        }
    }

    private fun arrayToString(list: List<String>): String {
        val builder = StringBuilder()
        list.forEach {
            builder.append(it).append(",")
        }
        builder.deleteCharAt(builder.lastIndex)

        return builder.toString()
    }
}
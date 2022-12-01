package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.MailSendException
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CaptchaMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.FreshmanInfo
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.StudentBriefInfo
import org.summer.chia.pojo.ao.StudentListItem
import org.summer.chia.pojo.dto.Captcha
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.StudentService
import org.summer.chia.utils.MailSendUtil
import org.summer.chia.utils.verificationCode
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import org.summer.chia.exception.TypeCastException

@Service
@EnableAspectJAutoProxy(exposeProxy = true)
class StudentServiceImp : ServiceImpl<StudentMapper, Student>(), StudentService {

    @Autowired
    private lateinit var mailSendUtils: MailSendUtil

    @Autowired
    private lateinit var captchaMapper: CaptchaMapper

    override fun loadUserByUsername(username: String): UserDetails? {
        val query = KtQueryWrapper(Student::class.java)
        query.eq(Student::studentNumber, username)
        val res = baseMapper.selectOne(query)
        return if (res != null) {
            res.role = "ROLE_Student"
            UserDetailsAdapter(res)
        } else
            null
    }

    override fun getBriefInfo(): Result {
        val account =
            ((SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).getPayLoad() as Student).studentNumber
        val query = KtQueryWrapper(Student::class.java)
        query.eq(Student::studentNumber, account)
        val res = baseMapper.selectOne(query)
        if (res != null) {
            return Result.success(
                StudentBriefInfo(
                    res.name,
                    res.studentNumber,
                    res.maxScore,
                    res.freeTimes,
                    res.status
                )
            )
        } else
            throw SqlException("Query Exceptions", this::getBriefInfo.name)
    }

    @Async
    @Transactional
    override fun enableAccount(mailAddress: String): Result {
        val uid = (SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).getPayLoad().id!!
        val code = verificationCode(System.currentTimeMillis())
        try {
            captchaMapper.insert(Captcha(null, uid, code, Timestamp(Date().time), 0))
            baseMapper.update(
                null,
                KtUpdateWrapper(Student::class.java).eq(Student::id, uid).set(Student::email, mailAddress)
            )
            val data = mapOf(
                "email" to mailAddress,
                "code" to code,
                "createTime" to SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")
            )
            mailSendUtils.sendTemplateMail(mailAddress, "邮箱绑定确认", "email_binding", data)
        } catch (e: Exception) {
            when (e) {
                is MailSendException -> throw e
                else -> throw throw SqlException("Insert or Update Exception", this::enableAccount.name)
            }

        }
        return Result.success()
    }

    @Transactional
    override fun importStudent(infos: List<FreshmanInfo>): Result {
        val bCryptPasswordEncoder = BCryptPasswordEncoder()
        try {
            infos.forEach {
                val password = it.idNumber.substring(it.idNumber.length - 8, it.idNumber.length)
                val date = SimpleDateFormat("yyyy-MM-dd").parse(it.enrollmentTime)
                baseMapper.insert(
                    Student(
                        null, it.name, bCryptPasswordEncoder.encode(password), it.studentNumber, it.idNumber,
                        0, 1, null, 0, java.sql.Date(date.time), null
                    )
                )
            }
        } catch (e: Exception) {
            throw SqlException("Insert Exception", this::importStudent.name)
        }
        return Result.success()
    }

    override fun queryStudentList(pageNum: String, pageSize: String): Result {
        try {
            val page = Page<Student>(pageNum.toLong(), pageSize.toLong())
            val students = baseMapper.selectPage(page, null)
            val res = ArrayList<StudentListItem>()
            students.records.forEach {
                val calendar = Calendar.getInstance()
                calendar.time = it.enrollmentTime
                res.add(
                    StudentListItem(
                        it.name,
                        it.studentNumber,
                        it.maxScore,
                        it.freeTimes,
                        calendar.get(Calendar.YEAR).toString() + "级"
                    )
                )
            }
            return Result.success(res)
        }catch (e : Exception){
            when(e){
                is NumberFormatException-> throw TypeCastException("Can not cast parameter to 'Long'",this::queryStudentList.name)
                else -> throw SqlException("Query Exception",this::queryStudentList.name)
            }
        }
    }

    override fun queryStudentTotalNumber(): Result {
        val res = baseMapper.selectCount(null)
        return Result.success(res)
    }

}
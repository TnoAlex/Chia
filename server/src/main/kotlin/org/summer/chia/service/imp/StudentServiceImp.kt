package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Base64Utils
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.MailSendException
import org.summer.chia.exception.SqlException
import org.summer.chia.exception.TypeCastException
import org.summer.chia.mapper.CaptchaMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.*
import org.summer.chia.pojo.dto.Captcha
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.StudentService
import org.summer.chia.utils.Log
import org.summer.chia.utils.MailSendUtil
import org.summer.chia.utils.verificationCode
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CompletableFuture

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

    override fun getBriefInfo(user: UserDetails): Result {
        val account =
            ((user as UserDetailsAdapter).getPayLoad() as Student).studentNumber
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
        } else {
            Log.error(this.javaClass, this::getBriefInfo.name + "-> Query Exceptions", null)
            throw SqlException("Query Exceptions", this::getBriefInfo.name)
        }

    }

    @Async
    @Transactional
    override fun enableAccount(mailAddress: String, user: UserDetails): CompletableFuture<Result> {
        val uid = (user as UserDetailsAdapter).getPayLoad().id!!
        val code = verificationCode(System.currentTimeMillis())
        val mail = Base64Utils.decode(mailAddress.toByteArray(Charsets.UTF_8)).toString(Charsets.UTF_8)
        try {
            if (captchaMapper.selectList(KtQueryWrapper(Captcha::class.java).eq(Captcha::uid, uid)).isNotEmpty()) {
                captchaMapper.delete(KtQueryWrapper(Captcha::class.java).eq(Captcha::uid, uid))
            }
            captchaMapper.insert(Captcha(null, uid, code, Timestamp(Date().time), 0))
            baseMapper.update(
                null,
                KtUpdateWrapper(Student::class.java).eq(Student::id, uid).set(Student::email, mail)
            )
            val data = mapOf(
                "email" to mail,
                "code" to code,
                "createTime" to SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(Date())
            )
            mailSendUtils.sendTemplateMail(mail, "邮箱绑定确认", "email_binding", data)
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::enableAccount.name + "-> Insert or Update Exception: " + e.message,
                e.stackTrace
            )
            when (e) {
                is MailSendException -> throw e
                else -> throw throw SqlException("Insert or Update Exception", this::enableAccount.name)
            }

        }
        return CompletableFuture.completedFuture(Result.success())
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
                        0, it.freeTime, null, 0, java.sql.Date(date.time), null
                    )
                )
            }
        } catch (e: Exception) {
            Log.error(this.javaClass, this::importStudent.name + " Insert Exception: " + e.message, e.stackTrace)
            throw SqlException("Insert Exception", this::importStudent.name)
        }
        return Result.success()
    }

    override fun queryStudentList(pageNum: String, pageSize: String): Result {
        try {
            val page = Page<Student>(pageNum.toLong(), pageSize.toLong())
            val students = baseMapper.selectPage(page, null)
            val calendar = Calendar.getInstance()
            val res = ArrayList<StudentListItem>()
            val totalSize = baseMapper.selectCount(null)
            students.records.forEach {
                calendar.time = it.enrollmentTime
                res.add(
                    StudentListItem(
                        it.id!!,
                        it.name,
                        it.studentNumber,
                        it.maxScore,
                        it.freeTimes,
                        calendar.get(Calendar.YEAR).toString() + "级",
                        totalSize,
                        null
                    )
                )
            }
            return Result.success(res)
        } catch (e: Exception) {
            Log.error(this.javaClass, this::queryStudentList.name + "-> " + e.message, e.stackTrace)
            when (e) {
                is NumberFormatException -> throw TypeCastException(
                    "Can not cast parameter to 'Long'",
                    this::queryStudentList.name
                )

                else -> throw SqlException("Query Exception", this::queryStudentList.name)
            }
        }
    }

    override fun queryStudentTotalNumber(): Result {
        val res = baseMapper.selectCount(null)
        return Result.success(res)
    }

    @Transactional
    override fun removeStudent(list: List<String>): Result {
        try {
            val query = KtQueryWrapper(Student::class.java)
            query.`in`(Student::id, list)
            baseMapper.delete(query)
            return Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::removeStudent.name + "-> Delete Exception: " + e.message, e.stackTrace)
            throw SqlException("Delete Exception", this::removeStudent.name)
        }
    }

    override fun doFilterStudentList(
        score: String,
        score_filter: String,
        grade: String,
        free_time: String,
        pageNum: String,
        pageSize: String
    ): Result {
        try {
            val query = filterQueryGen(score, score_filter, grade, free_time)
            if (query.isEmptyOfWhere)
                return Result.error("无效的查询条件")
            else {
                val page = Page<Student>(pageNum.toLong(), pageSize.toLong())
                val list = baseMapper.selectPage(page, query)
                val totalSize = baseMapper.selectCount(query)
                val res = ArrayList<StudentListItem>()
                val calendar = Calendar.getInstance()
                list.records.forEach {
                    calendar.time = it.enrollmentTime
                    res.add(
                        StudentListItem(
                            it.id!!,
                            it.name,
                            it.studentNumber,
                            it.maxScore,
                            it.freeTimes,
                            calendar.get(Calendar.YEAR).toString() + "级",
                            totalSize,
                            null
                        )
                    )
                }
                return Result.success(res)
            }
        } catch (e: Exception) {
            Log.error(this.javaClass, this::queryStudentList.name + "->" + e.message, e.stackTrace)
            when (e) {
                is NumberFormatException -> throw TypeCastException(
                    "Can not cast parameter to 'Long'",
                    this::queryStudentList.name
                )

                else -> throw SqlException("Query Exception", this::queryStudentList.name)
            }
        }
    }

    override fun queryStudentByNameOrNumber(name: String, number: String): Result {
        val query = KtQueryWrapper(Student::class.java)
        val calendar = Calendar.getInstance()
        if (name != "null") {
            query.eq(Student::name, name)
        } else if (number != "null") {
            query.eq(Student::studentNumber, number)
        }
        if (query.isEmptyOfWhere) {
            return Result.error("条件错误")
        } else {
            val userList = baseMapper.selectList(query) ?: return Result.error("无效的查询")
            val totalSize = baseMapper.selectCount(query)
            return Result.success(
                userList.map { user ->
                    calendar.time = user.enrollmentTime
                    StudentListItem(
                        user.id!!,
                        user.name,
                        user.studentNumber,
                        user.maxScore,
                        user.freeTimes,
                        calendar.get(Calendar.YEAR).toString() + "级",
                        totalSize,
                        null
                    )
                }
            )
        }
    }

    override fun doQueryDetails(user: UserDetails): Result {
        val account =
            ((user as UserDetailsAdapter).getPayLoad()) as Student
        var email = ""
        if (account.email != null && account.email!!.isNotBlank()) {
            email = "****@" + account.email!!.split("@")[1]
        }
        val idNumber = account.idNumber.replace(Regex("(?<=\\w{3})\\w(?=\\w{4})"), "*")
        return Result.success(
            StudentDetails(
                account.name,
                account.studentNumber,
                idNumber,
                account.maxScore,
                account.freeTimes,
                account.status,
                email
            )
        )
    }

    @Transactional
    override fun doFilterStudentDelete(score: String, score_filter: String, grade: String, free_time: String): Result {
        val query = filterQueryGen(score, score_filter, grade, free_time)
        if (query.isEmptyOfWhere)
            return Result.error("无效的条件")
        try {
            baseMapper.delete(query)
            return Result.success()
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::doFilterStudentDelete.name + "-> Delete Exception: " + e.message,
                e.stackTrace
            )
            throw SqlException("Delete Exception", this::doFilterStudentDelete.name)
        }
    }

    private fun filterQueryGen(
        score: String,
        score_filter: String,
        grade: String,
        free_time: String
    ): KtQueryWrapper<Student> {
        val query = KtQueryWrapper(Student::class.java)
        if (score != "null") {
            when (score_filter) {
                "1" -> query.gt(Student::maxScore, score.toInt())
                "2" -> query.lt(Student::maxScore, score.toInt())
                "4" -> query.eq(Student::maxScore, score.toInt())
                "5" -> query.ge(Student::maxScore, score.toInt())
                "6" -> query.le(Student::maxScore, score.toInt())
            }
        }
        if (grade != "null") {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(grade)
            query.eq(Student::enrollmentTime, java.sql.Date(date.time))
        }
        if (free_time != "null") {
            query.eq(Student::freeTimes, free_time.toInt())
        }
        return query
    }

}
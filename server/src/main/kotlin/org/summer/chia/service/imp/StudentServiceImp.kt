package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.DigestUtils
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CaptchaMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.dto.Student
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.StudentBriefInfo
import org.summer.chia.pojo.dto.Captcha
import org.summer.chia.service.StudentService
import org.summer.chia.utils.MailSendUtil
import org.summer.chia.utils.verificationCode
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date

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
        val code = verificationCode()
        captchaMapper.insert(Captcha(null, uid, code, Timestamp(Date().time)))
        baseMapper.update(
            null,
            KtUpdateWrapper(Student::class.java).eq(Student::id, uid).set(Student::email, mailAddress)
        )
        val data = mapOf(
            "email" to mailAddress,
            "code" to code,
            "createTime" to SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")
        )
        try {
            mailSendUtils.sendTemplateMail(mailAddress, "邮箱绑定确认", "email_binding", data)
        } catch (e: Exception) {
            throw e
        }
        return Result.success()
    }

}
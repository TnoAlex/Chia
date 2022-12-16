package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Base64Utils
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.MailSendException
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CaptchaMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.mapper.TeacherMapper
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Captcha
import org.summer.chia.pojo.dto.Student
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.pojo.dto.User
import org.summer.chia.service.CaptchaService
import org.summer.chia.service.UserService
import org.summer.chia.utils.Log
import org.summer.chia.utils.MailSendUtil
import org.summer.chia.utils.verificationCode
import java.sql.Timestamp
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
@EnableAspectJAutoProxy(exposeProxy = true)
class CaptchaServiceImp : ServiceImpl<CaptchaMapper, Captcha>(), CaptchaService {

    @Autowired
    private lateinit var studentMapper: StudentMapper

    @Autowired
    private lateinit var mailSendUtils: MailSendUtil

    @Autowired
    private lateinit var teacherMapper: TeacherMapper

    @Autowired
    private lateinit var userService: UserService

    @Transactional
    override fun doActivateAccount(code: String): Result {
        val res = baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, code))
            ?: return Result.error("验证码错误或已过期")
        try {
            return when (res.status) {
                -1 -> {
                    Result.error("验证码错误或已过期")
                }

                1 -> {
                    baseMapper.delete(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, code))
                    Result.error("验证码无效")
                }

                0 -> {
                    studentMapper.update(
                        null,
                        KtUpdateWrapper(Student::class.java).eq(Student::id, res.uid).set(Student::status, 1)
                    )
                    baseMapper.delete(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, code))
                    Result.success()
                }

                else -> {
                    Result.error("验证码错误或已过期")
                }
            }
        } catch (e: Exception) {
            Log.error(
                javaClass,
                this::doActivateAccount.name + "-> Delete or Update Exception: " + e.message,
                e.stackTrace
            )
            throw SqlException("Delete or Update Exception", this::doActivateAccount.name)
        }
    }

    @Async
    @Transactional
    override fun genRestPasswordCode(user: UserDetails): CompletableFuture<Result> {
        val account = (user as UserDetailsAdapter).getPayLoad()
        return try {
            genCode(account)
            CompletableFuture.completedFuture(Result.success())
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::genRestPasswordCode.name + "-> Insert Exception: " + e.message,
                e.stackTrace
            )
            when (e) {
                is MailSendException -> throw e
                else -> throw SqlException("Insert Exception", this::genRestPasswordCode.name)
            }
        }
    }

    private fun genCode(account: User) {
        val code = verificationCode(System.currentTimeMillis())
        val history = baseMapper.selectList(KtQueryWrapper(Captcha::class.java).eq(Captcha::uid, account.id!!))
        if (history.isNullOrEmpty()) {
            baseMapper.delete(KtQueryWrapper(Captcha::class.java).eq(Captcha::uid, account.id!!))
        }
        baseMapper.insert(Captcha(null, account.id!!, code, Timestamp(Date().time), 0))
        val data = mapOf("code" to code)
        mailSendUtils.sendTemplateMail(account.email!!, "您正在修改您的密码", "forget_password.html", data)
    }

    @Transactional
    override fun doValidateRestPasswordCode(code: String): Result {
        val captcha = baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, code))
            ?: return Result.error("无效的验证码")
        try {
            return when (captcha.status) {
                -1 -> Result.error("验证码已过期")
                0 -> {
                    captcha.status = 1
                    baseMapper.update(captcha, null)
                    Result.success()
                }

                1 -> Result.success()
                else -> Result.error("无效的验证码")
            }
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::doValidateRestPasswordCode.name + "-> Update Exception: " + e.message,
                e.stackTrace
            )
            throw SqlException("Update Exception", this::doValidateRestPasswordCode.name)
        }

    }

    @Transactional
    override fun evalCode(code: String): Boolean {
        try {
            val captcha =
                baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, code)) ?: return false
            baseMapper.delete(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, code))
            return captcha.status == 1
        } catch (e: Exception) {
            Log.error(this.javaClass, this::evalCode.name + "-> Delete Exception: " + e.message, e.stackTrace)
            throw SqlException("Delete Exception", this::evalCode.name)
        }

    }

    @Async
    @Transactional
    override fun forgetPassword(email: String): CompletableFuture<Result> {
        var user: User? = studentMapper.selectOne(
            KtQueryWrapper(Student::class.java).eq(
                Student::email,
                Base64Utils.decode(email.toByteArray(Charsets.UTF_8)).toString(Charsets.UTF_8)
            )
        )
        if (user == null) {
            user = teacherMapper.selectOne(
                KtQueryWrapper(Teacher::class.java).eq(
                    Teacher::email,
                    Base64Utils.decode(email.toByteArray(Charsets.UTF_8)).toString(Charsets.UTF_8)
                )
            )
        }
        if (user == null)
            return CompletableFuture.completedFuture(Result.error("没有找到与此邮箱匹配的账号"))
        return try {
            genCode(user)
            CompletableFuture.completedFuture(Result.success())
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::genRestPasswordCode.name + "-> Insert Exception: " + e.message,
                e.stackTrace
            )
            when (e) {
                is MailSendException -> throw e
                else -> throw SqlException("Insert Exception", this::genRestPasswordCode.name)
            }
        }
    }

    @Transactional
    override fun resetPassword(obj: RestPassword): Result {
        val uid = baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, obj.code)).uid
        var user: User? = null
        when (obj.type) {
            1 -> user = teacherMapper.selectOne(KtQueryWrapper(Teacher::class.java).eq(Teacher::id, uid))
            0 -> user = studentMapper.selectOne(KtQueryWrapper(Student::class.java).eq(Student::id, uid))
            else -> {
                baseMapper.delete(KtUpdateWrapper(Captcha::class.java).eq(Captcha::code, obj.code))
                return Result.error("此用户不存在")
            }
        }
        return userService.resetUserPassword(obj, UserDetailsAdapter(user!!))
    }

}
package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.MailSendException
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CaptchaMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Captcha
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.CaptchaService
import org.summer.chia.utils.Log
import org.summer.chia.utils.MailSendUtil
import org.summer.chia.utils.verificationCode
import java.sql.Timestamp
import java.util.*

@Service
@EnableAspectJAutoProxy(exposeProxy = true)
class CaptchaServiceImp : ServiceImpl<CaptchaMapper, Captcha>(), CaptchaService {

    @Autowired
    private lateinit var studentMapper: StudentMapper

    @Autowired
    private lateinit var mailSendUtils: MailSendUtil

    @Transactional
    override fun doActivateAccount(code: String): Result {
        val res = baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code, code))
            ?: return Result.error("验证码错误或已过期")
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
    }

    @Async
    @Transactional
    override fun genRestPasswordCode(): Result {
        val code = verificationCode(System.currentTimeMillis())
        val user = (SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).getPayLoad()
        return try{
            baseMapper.insert(Captcha(null,user.id!!,code, Timestamp(Date().time),0))
            val data = mapOf("code" to code)
            mailSendUtils.sendTemplateMail(user.email!!, "您正在修改您的密码", "forget_password.html", data)
            Result.success()
        }catch (e:Exception){
            Log.error(this.javaClass, this::genRestPasswordCode.name + " Insert Exception", e.suppressed)
            when (e){
                is MailSendException-> throw e
                else -> throw SqlException("Insert Exception",this::genRestPasswordCode.name)
            }
        }
    }

    @Transactional
    override fun doValidateRestPasswordCode(code: String): Result {
        val captcha = baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code,code)) ?: return Result.error("无效的验证码")
        return when(captcha.status){
            -1 -> Result.error("验证码已过期")
            0 ->{
                captcha.status = 1
                baseMapper.update(captcha,null)
                Result.success()
            }
            1 -> Result.success()
            else -> Result.error("无效的验证码")
        }
    }

    override fun evalCode(code: String):Boolean{
        val captcha = baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code,code)) ?: return false
        return captcha.status == 1
    }

}
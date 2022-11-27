package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.mapper.CaptchaMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Captcha
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.CaptchaService

@Service
class CaptchaServiceImp:ServiceImpl<CaptchaMapper, Captcha>(), CaptchaService {

    @Autowired
    private lateinit var studentMapper: StudentMapper

    @Transactional
    override fun doVerifyCode(code: String): Result {
        val res = baseMapper.selectOne(KtQueryWrapper(Captcha::class.java).eq(Captcha::code,code)) ?: return Result.error("验证码错误或已过期")
        studentMapper.update(null,KtUpdateWrapper(Student::class.java).eq(Student::id,res.uid).set(Student::status,1))
        return Result.success()
    }

}
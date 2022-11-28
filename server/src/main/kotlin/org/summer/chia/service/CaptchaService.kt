package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Captcha

interface CaptchaService:IService<Captcha> {
    fun doVerifyCode(code:String): Result
}
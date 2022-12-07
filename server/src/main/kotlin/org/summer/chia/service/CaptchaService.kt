package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Captcha

interface CaptchaService:IService<Captcha> {
    fun doActivateAccount(code:String): Result
    fun genRestPasswordCode():Result
    fun doValidateRestPasswordCode(code: String):Result
    fun evalCode(code: String):Boolean
}
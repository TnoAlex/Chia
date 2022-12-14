package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Captcha
import java.util.concurrent.CompletableFuture

interface CaptchaService:IService<Captcha> {
    fun doActivateAccount(code: String): Result
    fun genRestPasswordCode(user: UserDetails): CompletableFuture<Result>
    fun doValidateRestPasswordCode(code: String): Result
    fun evalCode(code: String): Boolean
    fun forgetPassword(email: String): CompletableFuture<Result>
    fun resetPassword(obj: RestPassword): Result
}
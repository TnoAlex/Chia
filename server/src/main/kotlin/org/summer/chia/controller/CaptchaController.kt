package org.summer.chia.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.CaptchaService

@Tag(name = "验证相关接口")
@RestController
class CaptchaController {

    @Autowired
    private lateinit var captchaService: CaptchaService

    @Operation(summary = "激活用户账号")
    @Parameter(`in` = ParameterIn.PATH, description = "发送到用户指定邮箱的验证码", required = true)
    @PostMapping("/student/verify/activate_account/{code}")
    fun activateStudentAccount(@PathVariable code: String): Result {
        return captchaService.doActivateAccount(code)
    }

    @Operation(summary = "获取重置密码验证码")
    @Parameter(hidden = true)
    @GetMapping("/verify/reset_code")
    fun getRestPasswordCode(@AuthenticationPrincipal user: UserDetails): Result {
        val task = captchaService.genRestPasswordCode(user)
        return task.get()
    }

    @Operation(summary = "验证重置密码验证码")
    @Parameter(`in` = ParameterIn.PATH, description = "发送到用户邮箱的验证码", required = false)
    @PostMapping("/verify/reset_code/validate/{code}")
    fun validateRestPasswordCode(@PathVariable code: String): Result {
        return captchaService.doValidateRestPasswordCode(code)
    }

    @Operation(summary = "获取忘记密码验证码")
    @Parameter(`in` = ParameterIn.PATH, description = "用户事先绑定的邮箱地址", required = true)
    @PostMapping("/forget/password/email/{email}")
    fun forgetPassword(@PathVariable email: String): Result? {
        val task = captchaService.forgetPassword(email)
        return task.get()
    }

    @Operation(summary = "忘记密码重置密码")
    @Parameter(description = "重置密码", schema = Schema(implementation = RestPassword::class), required = true)
    @PostMapping("/forget/password/rest")
    fun resetPassword(@RequestBody obj: RestPassword): Result {
        return captchaService.resetPassword(obj)
    }
}
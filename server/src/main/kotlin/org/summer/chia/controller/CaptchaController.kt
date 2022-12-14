package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.CaptchaService

@RestController
class CaptchaController {

    @Autowired
    private lateinit var captchaService: CaptchaService

    @PostMapping("/student/verify/activate_account/{code}")
    fun activateStudentAccount(@PathVariable code: String): Result {
        return captchaService.doActivateAccount(code)
    }

    @GetMapping("/verify/reset_code")
    fun getRestPasswordCode(@AuthenticationPrincipal user: UserDetails): Result {
        val task = captchaService.genRestPasswordCode(user)
        return task.get()
    }

    @PostMapping("/verify/reset_code/validate/{code}")
    fun validateRestPasswordCode(@PathVariable code: String): Result {
        return captchaService.doValidateRestPasswordCode(code)
    }

    @PostMapping("/forget/password/email/{email}")
    fun forgetPassword(@PathVariable email: String): Result? {
        val task = captchaService.forgetPassword(email)
        return task.get()
    }

    @PostMapping("/forget/password/rest")
    fun resetPassword(@RequestBody obj: RestPassword): Result {
        return captchaService.resetPassword(obj)
    }
}
package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
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
    fun getRestPasswordCode(): Result {
        return captchaService.genRestPasswordCode()
    }

    @PostMapping("/verify/reset_code/validate/{code}")
    fun validateRestPasswordCode(@PathVariable code: String): Result {
        return captchaService.doValidateRestPasswordCode(code)
    }
}
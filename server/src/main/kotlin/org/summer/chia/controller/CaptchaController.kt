package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.CaptchaService

@RestController
class CaptchaController {

    @Autowired
    private lateinit var captchaService: CaptchaService

    @PostMapping("/user/verify/{code}")
    fun verifyCode(@PathVariable code: String): Result {
        return captchaService.doVerifyCode(code)
    }
}
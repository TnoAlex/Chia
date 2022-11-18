package org.summer.chia.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.pojo.ao.Result

@RestController
class LoginController {


    @PostMapping("/login/page")
    fun login(): Result {
        return Result.success("登录页面")
    }

    @PostMapping("/test/login")
    fun testLogin(): Result {
        return Result.success("登录成功")
    }
}
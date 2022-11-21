package org.summer.chia.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.summer.chia.pojo.ao.Result

@Controller
class LoginController {

    @GetMapping("/login/page")
    fun login(model: Model): String {
        return "login"
    }

    @ResponseBody
    @PostMapping("/test/login")
    fun testLogin(): Result {
        return Result.success("登录成功")
    }
}
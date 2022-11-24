package org.summer.chia.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import java.net.http.HttpRequest

@Controller
class ErrorController {

    @PostMapping("/error/40x")
    fun error40x(request: HttpRequest) {
        TODO()
    }
}
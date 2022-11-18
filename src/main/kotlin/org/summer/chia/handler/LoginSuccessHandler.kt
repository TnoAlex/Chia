package org.summer.chia.handler

import com.google.gson.Gson
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.ResultStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        if (authentication.principal == null) {
            response.writer.write(Gson().toJson(Result.error(ResultStatus.BAD_AUTHENTICATION)))
        } else {
            response.writer.write(Gson().toJson(Result.success("登录成功")))
        }
    }
}
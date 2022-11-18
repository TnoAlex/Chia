package org.summer.chia.handler

import com.google.gson.Gson
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.ResultStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthorizationDeniedHandler : AuthenticationFailureHandler, AuthenticationEntryPoint, AccessDeniedHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        val res = exception.message?.let { Result.error(it) }
        response.writer.write(Gson().toJson(res))
    }

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.writer.write(Gson().toJson(Result.error(ResultStatus.NONE_AUTHENTICATION)))
    }

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val res = accessDeniedException.message?.let { Result.error(it) }
        response.writer.write(Gson().toJson(res))
    }
}
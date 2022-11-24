package org.summer.chia.handler

import com.google.gson.Gson
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.summer.chia.pojo.ao.Result
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
        handlerException(request, response, authException)
    }

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        handlerException(request, response, accessDeniedException)
    }

    private fun handlerException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: RuntimeException
    ) {
        val ex = request.getAttribute("javax.servlet.error.exception") as RuntimeException?
        if (ex == null) {
            response.writer.write(Gson().toJson(exception.message?.let { Result.error(it) }))
        } else {
            when (ex) {
                is AuthenticationException -> {
                    response.writer.write(Gson().toJson(ex.message?.let { Result.error(it) }))
                }

                is AccessDeniedException -> {
                    request.setAttribute("msg", ex.message)
                    request.getRequestDispatcher("/error/40x").forward(request, response)
                    return
                }

                else -> {
                    response.writer.write(Gson().toJson(exception.message?.let { Result.error(it) }))
                }
            }
        }
    }
}
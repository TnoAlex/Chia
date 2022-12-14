package org.summer.chia.filter

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.ResultStatus
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter : OncePerRequestFilter() {

    @Value("\${springdoc.api-docs.enabled}")
    private var docEnable: Boolean = false

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        if (docEnable) {
            if (request.servletPath.contains("/swagger-ui") || request.servletPath.contains("/v3")) {
                chain.doFilter(request, response)
                return
            }
        }
        if (request.servletPath.contains("/forget") || request.servletPath.contains("/verify")) {
            chain.doFilter(request, response)
            return
        }
        if (SecurityContextHolder.getContext().authentication == null) {
            response.writer.write(Gson().toJson(Result.error(ResultStatus.NONE_AUTHENTICATION)))
            return
        }
        if (SecurityContextHolder.getContext().authentication.isAuthenticated) {
            chain.doFilter(request, response)
            return
        } else {
            response.writer.write(Gson().toJson(Result.error(ResultStatus.BAD_AUTHENTICATION)))
            return
        }
    }
}
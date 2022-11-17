package org.summer.chia.filter

import com.google.gson.Gson
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.ResultStatus
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class AuthenticationFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        if (!(request as HttpServletRequest).servletPath.equals("/") && !request.servletPath.contains("login")) {
            if (SecurityContextHolder.getContext().authentication == null) {
                response.writer.write(Gson().toJsonTree(Result.error(ResultStatus.BAD_AUTHENTICATION)).asString)
                return
            }
            if (SecurityContextHolder.getContext().authentication.isAuthenticated) {
                chain.doFilter(request, response)
            } else {
                response.writer.write(Gson().toJsonTree(Result.error(ResultStatus.BAD_AUTHENTICATION)).asString)
                return
            }
        }
    }
}
package org.summer.chia.filter

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import org.summer.chia.pojo.ao.LoginObject
import org.summer.chia.security.UsernamePasswordRoleAuthenticationToken
import java.io.*
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationProcessingFilter : UsernamePasswordAuthenticationFilter() {
    @Autowired
    override fun setAuthenticationManager(authenticationManager: AuthenticationManager) {
        super.setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        if (request.method != "POST") {
            throw AuthenticationServiceException("不支持的请求方式")
        } else {
            val entity = Gson().fromJson(RequestWrapper(request).body, LoginObject::class.java)
            val authRequest = UsernamePasswordRoleAuthenticationToken(entity.username, entity.password, entity.type)
            authRequest.details = this.authenticationDetailsSource.buildDetails(request)
            return this.authenticationManager.authenticate(authRequest)
        }
    }

    inner class RequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

        override fun getReader(): BufferedReader {
            return BufferedReader(InputStreamReader(this.inputStream))
        }

        var body: String

        init {
            val stringBuilder = StringBuilder()
            var bufferedReader: BufferedReader? = null
            var inputStream: InputStream? = null
            try {
                inputStream = request.inputStream
                if (inputStream != null) {
                    bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val charBuffer = CharArray(128)
                    var bytesRead = -1
                    do {
                        bytesRead = bufferedReader.read(charBuffer)
                        if (bytesRead < 0) break
                        stringBuilder.append(charBuffer, 0, bytesRead)
                    } while (bytesRead > 0)
                }
            } catch (e: IOException) {
                throw e
            } finally {
                try {
                    inputStream?.close()
                    bufferedReader?.close()
                } catch (e: IOException) {
                    throw e
                }
            }
            this.body = stringBuilder.toString()
        }

        override fun getInputStream(): ServletInputStream {
            val byteArrayStream = ByteArrayInputStream(this.body.toByteArray(Charsets.UTF_8))
            val servletInputStream = object : ServletInputStream() {
                override fun read(): Int = byteArrayStream.read()

                override fun isFinished(): Boolean = false

                override fun isReady(): Boolean = false

                override fun setReadListener(listener: ReadListener?) {
                }
            }
            return servletInputStream
        }
    }
}
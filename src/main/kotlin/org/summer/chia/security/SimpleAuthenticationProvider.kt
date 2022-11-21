package org.summer.chia.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.summer.chia.adapter.UserDetailsAdapter

@Component
class SimpleAuthenticationProvider : AuthenticationProvider {

    private val passwordEncoder = BCryptPasswordEncoder()

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials as String
        val role = (authentication as UsernamePasswordRoleAuthenticationToken).roleType
        var user = userDetailsService.loadUserByUsername("$username/$role")
        user = user as UserDetailsAdapter
        return if (passwordEncoder.matches(password, user.password)) {
            val loginJwtToken = UsernamePasswordAuthenticationToken(user.getPayLoad(), password, user.authorities)
            SecurityContextHolder.getContext().authentication = loginJwtToken
            UsernamePasswordAuthenticationToken(user, password, user.authorities)
        } else {
            throw BadCredentialsException("The user name and password do not match")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean =
        authentication == UsernamePasswordRoleAuthenticationToken::class.java
}
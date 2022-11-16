package org.summer.chia.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
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

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials as String
        var user = userDetailsService.loadUserByUsername(username)
        if (user == null) {
            SecurityContextHolder.getContext().authentication = null
            return UsernamePasswordAuthenticationToken(null, null, null)
        } else {
            user = user as UserDetailsAdapter
        }
        return if (passwordEncoder.matches(password, user.password)) {
            val loginJwtToken = UsernamePasswordAuthenticationToken(user.getPayLoad(), password, user.authorities)
            SecurityContextHolder.getContext().authentication = loginJwtToken
            UsernamePasswordAuthenticationToken(user, password, user.authorities)
        } else {
            SecurityContextHolder.getContext().authentication = null
            UsernamePasswordAuthenticationToken(null, null, null)
        }
    }

    override fun supports(authentication: Class<*>?): Boolean =
        authentication == UsernamePasswordAuthenticationToken::class.java
}
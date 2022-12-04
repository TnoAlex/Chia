package org.summer.chia.factory

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.annotation.WithMockTeacher
import org.summer.chia.pojo.dto.Teacher


class WithMockTeacherSecurityContextFactory : WithSecurityContextFactory<WithMockTeacher> {
    override fun createSecurityContext(annotation: WithMockTeacher): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()
        val auth = UsernamePasswordAuthenticationToken(
            UserDetailsAdapter(
                Teacher(
                    annotation.id,
                    annotation.name,
                    annotation.password,
                    annotation.email,
                    annotation.role
                )
            ), annotation.password, mutableListOf(SimpleGrantedAuthority(annotation.role))
        )
        context.authentication = auth
        return context
    }
}
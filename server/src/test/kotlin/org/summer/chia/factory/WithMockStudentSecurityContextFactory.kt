package org.summer.chia.factory

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.annotation.WithMockStudent
import org.summer.chia.pojo.dto.Student
import java.sql.Date
import java.text.SimpleDateFormat


class WithMockStudentSecurityContextFactory : WithSecurityContextFactory<WithMockStudent> {

    override fun createSecurityContext(annotation: WithMockStudent): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()
        val format = SimpleDateFormat("yyyy-MM-dd")
        val auth = UsernamePasswordAuthenticationToken(
            UserDetailsAdapter(
                Student(
                    annotation.id,
                    annotation.name,
                    annotation.password,
                    annotation.studentNumber,
                    annotation.idNumber,
                    annotation.maxScore,
                    annotation.freeTimes,
                    annotation.email,
                    annotation.status,
                    Date(format.parse(annotation.enrollmentTime).time),
                    annotation.role
                )
            ), annotation.password, mutableListOf(SimpleGrantedAuthority(annotation.role))
        )
        context.authentication = auth
        return context
    }
}
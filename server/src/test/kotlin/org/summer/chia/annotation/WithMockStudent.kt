package org.summer.chia.annotation

import org.springframework.security.test.context.support.WithSecurityContext
import org.summer.chia.factory.WithMockStudentSecurityContextFactory


@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockStudentSecurityContextFactory::class)
annotation class WithMockStudent(
    val id: String = "1593439666370023425",
    val name: String = "test",
    val password: String = "\$10\$VRI/n8AEHx6IxrbcPfk4SOATR.OeQ.bRud1pIKjJJIATK19tD3P2y",
    val studentNumber: String = "123456",
    val idNumber: String = "123456",
    val maxScore: Int = 300,
    val freeTimes: Int = 1,
    val email: String = "test@123.com",
    val status: Int = 1,
    val enrollmentTime: String = "2020-09-10",
    val role: String = "ROLE_Student"
)

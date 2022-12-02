package org.summer.chia.annotation

import org.springframework.security.test.context.support.WithSecurityContext
import org.summer.chia.factory.WithMockTeacherSecurityContextFactory

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockTeacherSecurityContextFactory::class)
annotation class WithMockTeacher(
    val id: String = "1593439665958981633",
    val name: String = "test",
    val password: String = "\$10\$UCkKJE0SfCgAtC7ju99ELedn6CY2twcQBSTKM.HwP6CXr.cQDKFr2",
    val email: String = "test@123.com",
    val role: String = "ROLE_Teacher"
)

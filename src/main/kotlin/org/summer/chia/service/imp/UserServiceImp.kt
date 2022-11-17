package org.summer.chia.service.imp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.summer.chia.exception.RoleTypeErrorException
import org.summer.chia.service.StudentService
import org.summer.chia.service.TeacherService
import org.summer.chia.service.UserService

@Service
class UserServiceImp : UserService {

    @Autowired
    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var teacherService: TeacherService

    override fun loadUserByUsername(username: String): UserDetails? {
        val usernameAndRole = username.split("/")
        val name = usernameAndRole[0]
        return when (usernameAndRole[1]) {
            "1" -> {
                teacherService.loadUserByUsername(name)
            }

            "0" -> {
                studentService.loadUserByUsername(name)
            }

            else -> {
                throw RoleTypeErrorException("Incorrect Permission Descriptor")
            }
        }
    }
}
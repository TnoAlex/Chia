package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.StudentService
import org.summer.chia.service.TeacherService

@RestController
class UserController {

    @Autowired
    private lateinit var teacherService: TeacherService

    @Autowired
    private lateinit var studentService: StudentService


    @GetMapping("/user/teacher/brief_info")
    fun teacherInfo(): Result {
        return teacherService.getUserNameByAccount()
    }

    @GetMapping("/user/student/brief_info")
    fun studentBriefInfo(): Result {
        return studentService.getBriefInfo()
    }
}
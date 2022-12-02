package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.pojo.ao.FreshmanInfo
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.StudentService
import org.summer.chia.service.TeacherService
import org.summer.chia.service.UserService

@RestController

class UserController {

    @Autowired
    private lateinit var teacherService: TeacherService

    @Autowired
    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var userService: UserService


    @GetMapping("/teacher/brief_info")
    fun teacherInfo(): Result {
        return teacherService.getUserNameByAccount()
    }

    @GetMapping("/student/brief_info")
    fun studentBriefInfo(): Result {
        return studentService.getBriefInfo()
    }

    @PostMapping("/student/enable/{mail}")
    fun enableAccount(@PathVariable mail: String): Result {
        return studentService.enableAccount(mail)
    }

    @PostMapping("/teacher/import/freshman")
    fun importFreshManInfo(@RequestBody infoList: List<FreshmanInfo>): Result {
        return studentService.importStudent(infoList)
    }

    @PostMapping("/user/reset/password")
    fun resetPassword(@RequestBody obj: RestPassword): Result {
        return userService.resetUserPassword(obj)
    }

    @GetMapping("/student/list/{pageNum}/{pageSize}")
    fun queryStudentList(@PathVariable pageNum: String, @PathVariable pageSize: String): Result {
        return studentService.queryStudentList(pageNum, pageSize)
    }

    @GetMapping("/student/list/total")
    fun queryStudentTotalNum(): Result {
        return studentService.queryStudentTotalNumber()
    }

    @PostMapping("/teacher/admin/remove/{sid}")
    fun removeStudent(@PathVariable sid: String): Result {
        return studentService.removeStudent(sid)
    }

    @GetMapping("/student/filter/{score}/{score_filter}/{grade}/{free_time}/{pageNum}/{pageSize}")
    fun filterStudentList(@PathVariable score: String, @PathVariable score_filter: String, @PathVariable grade: String,
                          @PathVariable free_time: String, @PathVariable pageNum: String, @PathVariable pageSize: String
    ): Result {
        return studentService.doFilterStudentList(score, score_filter, grade, free_time, pageNum, pageSize)
    }

    @GetMapping("/student/filter/{name}/{number}")
    fun queryStudentByNameOrNumber(@PathVariable name: String, @PathVariable number: String): Result {
        return studentService.queryStudentByNameOrNumber(name, number)
    }
}
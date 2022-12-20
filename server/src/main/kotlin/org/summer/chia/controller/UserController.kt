package org.summer.chia.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.*
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.StudentService
import org.summer.chia.service.TeacherService
import org.summer.chia.service.UserService

@RestController
@Tag(name = "用户模块")
class UserController {

    @Autowired
    private lateinit var teacherService: TeacherService

    @Autowired
    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var userService: UserService

    @Operation(description = "老师简要信息")
    @Parameter(hidden = true)
    @ApiResponse(description = "老师姓名", responseCode = "200")
    @GetMapping("/teacher/brief_info")
    fun teacherInfo(@AuthenticationPrincipal user: UserDetails): Result {
        return teacherService.getUserNameByAccount(user)
    }

    @Operation(description = "学生简要信息")
    @Parameter(hidden = true)
    @ApiResponse(
        responseCode = "200",
        description = "学生简要信息",
        content = [Content(schema = Schema(implementation = StudentBriefInfo::class))]
    )
    @GetMapping("/student/brief_info")
    fun studentBriefInfo(@AuthenticationPrincipal user: UserDetails): Result {
        return studentService.getBriefInfo(user)
    }

    @Operation(description = "激活账号")
    @Parameter(name = "user", hidden = true, required = true)
    @Parameter(name = "mail", `in` = ParameterIn.PATH, required = true, description = "绑定的邮箱")
    @ApiResponse(description = "老师姓名", responseCode = "200")
    @PostMapping("/student/enable/{mail}")
    fun enableAccount(@PathVariable mail: String, @AuthenticationPrincipal user: UserDetails): Result {
        val task = studentService.enableAccount(mail, user)
        return task.get()
    }

    @Operation(description = "导入学生名单")
    @Parameter(name = "infoList", description = "导入学生名单", schema = Schema(implementation = FreshmanInfo::class))
    @PostMapping("/teacher/import/freshman")
    fun importFreshManInfo(@RequestBody infoList: List<FreshmanInfo>): Result {
        return studentService.importStudent(infoList)
    }

    @Operation(description = "用户重置密码")
    @Parameter(name = "user", hidden = true, required = true)
    @Parameter(
        name = "obj",
        description = "重置密码实体",
        required = true,
        schema = Schema(implementation = RestPassword::class)
    )
    @PostMapping("/user/reset/password")
    fun resetPassword(@RequestBody obj: RestPassword, @AuthenticationPrincipal user: UserDetails): Result {
        return userService.resetUserPassword(obj, user)
    }

    @Operation(summary = "获取学生列表")
    @Parameters(
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @ApiResponse(
        responseCode = "200",
        description = "学生列表",
        content = [Content(schema = Schema(implementation = StudentListItem::class))]
    )
    @GetMapping("/teacher/list/{pageNum}/{pageSize}")
    fun queryStudentList(@PathVariable pageNum: String, @PathVariable pageSize: String): Result {
        return studentService.queryStudentList(pageNum, pageSize)
    }

    @Deprecated("接口已整合", level = DeprecationLevel.ERROR)
    @GetMapping("/teacher/list/total")
    fun queryStudentTotalNum(): Result {
        return studentService.queryStudentTotalNumber()
    }

    @Operation(description = "移除学生")
    @Parameter(description = "需要移除的学生的ID列表", required = true)
    @PostMapping("/teacher/admin/remove")
    fun removeStudent(@RequestBody list: List<String>): Result {
        return studentService.removeStudent(list)
    }

    @Operation(summary = "获取学生列表")
    @Parameters(
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @GetMapping("/teacher/filter/{score}/{score_filter}/{grade}/{free_time}/{pageNum}/{pageSize}")
    fun filterStudentList(
        @PathVariable score: String, @PathVariable score_filter: String, @PathVariable grade: String,
        @PathVariable free_time: String, @PathVariable pageNum: String, @PathVariable pageSize: String
    ): Result {
        return studentService.doFilterStudentList(score, score_filter, grade, free_time, pageNum, pageSize)
    }

    @Operation(summary = "过滤学生列表")
    @Parameters(
        Parameter(name = "score", description = "成绩", required = true, `in` = ParameterIn.PATH),
        Parameter(
            name = "score_filter",
            description = "成绩条件 1 大于,2 小于,4 等于,5 大于等于,6 小于等于",
            required = true,
            `in` = ParameterIn.PATH,
            example = "1"
        ),
        Parameter(name = "grade", description = "年级", required = true, `in` = ParameterIn.PATH, example = "2020-9-1"),
        Parameter(name = "free_time", description = "免费次数", required = true, `in` = ParameterIn.PATH)
    )
    @ApiResponse(
        responseCode = "200",
        description = "学生列表",
        content = [Content(schema = Schema(implementation = StudentListItem::class))]
    )
    @PostMapping("/teacher/filter/{score}/{score_filter}/{grade}/{free_time}")
    fun filterStudentDelete(
        @PathVariable score: String, @PathVariable score_filter: String, @PathVariable grade: String,
        @PathVariable free_time: String
    ): Result {
        return studentService.doFilterStudentDelete(score, score_filter, grade, free_time)
    }

    @Operation(summary = "按照姓名或学号查找")
    @Parameters(
        Parameter(name = "name", description = "姓名", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "number", description = "学号", required = true, `in` = ParameterIn.PATH),
    )
    @ApiResponse(
        responseCode = "200",
        description = "学生列表",
        content = [Content(schema = Schema(implementation = StudentListItem::class))]
    )
    @GetMapping("/teacher/filter/{name}/{number}")
    fun queryStudentByNameOrNumber(@PathVariable name: String, @PathVariable number: String): Result {
        return studentService.queryStudentByNameOrNumber(name, number)
    }

    @Operation(summary = "查询学生详细信息")
    @Parameter(hidden = true)
    @GetMapping("/student/query/details")
    @ApiResponse(
        responseCode = "200",
        description = "学生详细",
        content = [Content(schema = Schema(implementation = StudentDetails::class))]
    )
    fun queryStudentDetails(@AuthenticationPrincipal user: UserDetails): Result {
        return studentService.doQueryDetails(user)
    }

    @Operation(summary = "添加老师")
    @Parameter(
        name = "obj",
        required = true,
        schema = Schema(implementation = Teacher::class),
        description = "老师实体"
    )
    @PostMapping("/teacher/add/teacher")
    fun addTeacher(@RequestBody obj: Teacher, @AuthenticationPrincipal user: UserDetails): Result {
        return teacherService.addTeacher(obj, user)
    }

    @Operation(summary = "删除老师")
    @Parameter(name = "tid", description = "要删除的老师id", `in` = ParameterIn.PATH, required = true)
    @PostMapping("/teacher/delete/teacher/{tid}")
    fun deleteTeacher(@PathVariable tid: String): Result {
        return teacherService.deleteTeacher(tid)
    }

    @Operation(summary = "获取老师列表")
    @ApiResponse(
        responseCode = "200", description = "老师列表", content = [Content(
            schema = Schema(implementation = CommunicativeTarget::class)
        )]
    )
    @GetMapping("/teacher/query/list")
    fun queryTeacherList(): Result {
        return teacherService.queryTeacherList()
    }
}
package org.summer.chia.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.RegistrationListItem
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.RegistrationService

@Tag(name = "CSP正式报名模块")
@RestController
class CspRegistrationController {
    @Autowired
    private lateinit var registrationService: RegistrationService

    @Operation(summary = "上传成绩单")
    @Parameter(description = "成绩单列表", required = true)
    @PostMapping("/teacher/upload/transcripts")
    fun uploadTranscripts(@RequestBody objList: List<RegistrationListItem>): Result {
        return registrationService.transcriptsList(objList)
    }

    @Operation(summary = "上传正式报名名单")
    @Parameter(description = "正式报名名单", required = true)
    @PostMapping("/teacher/upload/regList")
    fun uploadRegistrationList(@RequestBody objList: List<RegistrationListItem>): Result {
        return registrationService.registrationList(objList)
    }

    @Operation(summary = "获取未正式报名列表")
    @Parameters(
        Parameter(name = "cid", description = "查询的CSP ID", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @GetMapping("/teacher/query/absent/official/{cid}/{pageNum}/{pageSize}")
    fun queryAbsentOfficialRegistration(
        @PathVariable cid: String, @PathVariable pageNum: String,
        @PathVariable pageSize: String
    ): Result {
        return registrationService.doQueryAbsentOfficialRegistration(cid, pageNum, pageSize)
    }


    @Operation(summary = "获取正式名列表")
    @Parameters(
        Parameter(name = "cid", description = "查询的CSP ID", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @GetMapping("/teacher/query/official/{cid}/{pageNum}/{pageSize}")
    fun queryOfficialList(
        @PathVariable cid: String,
        @PathVariable pageNum: String,
        @PathVariable pageSize: String
    ): Result {
        return registrationService.queryOfficialList(cid, pageNum, pageSize)
    }

    @Operation(summary = "获取缺席考试列表")
    @Parameters(
        Parameter(name = "cid", description = "查询的CSP ID", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @GetMapping("/teacher/query/absent/exam/{cid}/{pageNum}/{pageSize}")
    fun queryAbsentExam(
        @PathVariable cid: String,
        @PathVariable pageNum: String,
        @PathVariable pageSize: String
    ): Result {
        return registrationService.doQueryAbsentExam(cid, pageNum, pageSize)
    }

    @Operation(summary = "提醒学生进行正式报名")
    @Parameters(
        Parameter(name = "cid", description = "查询的CSP ID", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "user", required = true, hidden = true)
    )
    @PostMapping("/teacher/notice/official/{cid}")
    fun noticeStudent(
        @PathVariable cid: String,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return registrationService.noticeStudent(cid, user)
    }
}
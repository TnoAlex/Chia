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
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.StudentListItem
import org.summer.chia.pojo.dto.PreRegistration
import org.summer.chia.service.PreRegistrationService

@Tag(name = "CSP预报名模块")
@RestController
class CspPreRegistrationController {

    @Autowired
    private lateinit var preRegistrationService: PreRegistrationService

    @Operation(summary = "学生预报名")
    @Parameter(
        name = "preRegistratio",
        description = "预报名实体",
        required = true,
        schema = Schema(implementation = PreRegistration::class)
    )
    @Parameter(name = "user", required = true, schema = Schema(implementation = UserDetails::class), hidden = true)
    @PostMapping("/pre/reg")
    fun preRegistration(
        @RequestBody preRegistration: PreRegistration,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return preRegistrationService.doPreRegistration(preRegistration, user)
    }

    @Operation(summary = "取消预报名")
    @Parameter(description = "要取消的CSP ID", required = true, `in` = ParameterIn.PATH)
    @Parameter(
        name = "user",
        required = true,
        schema = Schema(implementation = UserDetails::class, hidden = true),
        hidden = true
    )
    @PostMapping("/pre/reg/cancel/{cid}")
    fun cancelPreRegistration(@PathVariable cid: String, @AuthenticationPrincipal user: UserDetails): Result {
        return preRegistrationService.doCancelPreRegistration(cid, user)
    }

    @Operation(summary = "获取预报名列表")
    @Parameters(
        Parameter(name = "cid", description = "查询的CSP ID", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @ApiResponse(
        responseCode = "200",
        description = "预报名的学生列表",
        content = [Content(schema = Schema(implementation = StudentListItem::class))]
    )
    @GetMapping("/pre/reg/list/{cid}/{pageNum}/{pageSzie}")
    fun queryPreRegistrationList(
        @PathVariable cid: String, @PathVariable pageNum: String,
        @PathVariable pageSzie: String
    ): Result {
        return preRegistrationService.doQueryList(cid, pageNum, pageSzie)
    }
}
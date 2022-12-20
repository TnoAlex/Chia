package org.summer.chia.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.PublishCsp
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.CspInfoService


@Tag(name = "CSP信息接口")
@RestController
class CspInfoController {

    @Autowired
    private lateinit var cspInfoService: CspInfoService

    @Operation(summary = "进行CSP预报名")
    @GetMapping("/csp_info/pre")
    @Parameter(hidden = true)
    fun allPreRegistrationInfo(@AuthenticationPrincipal user: UserDetails): Result {
        return cspInfoService.getAllPreRegistrationBriefInfo(user)
    }

    @Operation(summary = "教师发布CSP预报名")
    @Parameter(description = "CSP发布信息", required = true, schema = Schema(implementation = PublishCsp::class))
    @PostMapping("/teacher/publish/csp")
    fun publishCspRegistration(@RequestBody obj: PublishCsp): Result {
        return cspInfoService.addCsp(obj)
    }

    @Operation(summary = "教师修改CSP预报名信息")
    @Parameter(required = true, description = "其中只需要保留需要修改的部分")
    @PostMapping("/teacher/publish/modify")
    fun modifyCspRegistration(@RequestBody obj: PublishCsp): Result {
        return cspInfoService.modifyCspInfo(obj)
    }

    @Operation(summary = "教师删除CSP预报名信息")
    @Parameter(description = "CSP ID", `in` = ParameterIn.PATH, required = true)
    @PostMapping("/teacher/publish/delete/{pid}")
    fun deleteCspRegistration(@PathVariable pid: String): Result {
        return cspInfoService.deleteCspInfo(pid)
    }
}
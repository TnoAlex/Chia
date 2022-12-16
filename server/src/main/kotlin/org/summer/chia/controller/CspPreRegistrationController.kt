package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.PreRegistration
import org.summer.chia.service.PreRegistrationService

@RestController
class CspPreRegistrationController {

    @Autowired
    private lateinit var preRegistrationService: PreRegistrationService

    @PostMapping("/pre/reg")
    fun preRegistration(
        @RequestBody preRegistration: PreRegistration,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return preRegistrationService.doPreRegistration(preRegistration, user)
    }

    @PostMapping("/pre/reg/cancel/{pid}")
    fun cancelPreRegistration(@PathVariable pid: String, @AuthenticationPrincipal user: UserDetails): Result {
        return preRegistrationService.doCancelPreRegistration(pid, user)
    }

    @GetMapping("/pre/reg/list/{cid}/{pageNum}/{pageSzie}")
    fun queryPreRegistrationList(
        @PathVariable cid: String, @PathVariable pageNum: String,
        @PathVariable pageSzie: String
    ): Result {
        return preRegistrationService.doQueryList(cid, pageNum, pageSzie)
    }
}
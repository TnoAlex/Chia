package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.CspInfoService

@RestController
class CspInfoController {

    @Autowired
    private lateinit var cspInfoService: CspInfoService

    @GetMapping("/csp_info/pre")
    fun allPreRegistrationInfo(): Result {
        return cspInfoService.getAllPreRegistrationBriefInfo()
    }
}
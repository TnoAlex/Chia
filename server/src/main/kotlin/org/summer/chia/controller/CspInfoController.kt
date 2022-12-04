package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.PublishCsp
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

    @PostMapping("/teacher/publish/csp")
    fun publishCspRegistration(@RequestBody obj: PublishCsp): Result {
        return cspInfoService.addCsp(obj)
    }

    @PostMapping("/teacher/publish/modify")
    fun modifyCspRegistration(@RequestBody obj: PublishCsp): Result {
        return cspInfoService.modifyCspInfo(obj)
    }

    @PostMapping("/teacher/publish/delete/{pid}")
    fun deleteCspRegistration(@PathVariable pid: String): Result {
        return cspInfoService.deleteCspInfo(pid)
    }
}
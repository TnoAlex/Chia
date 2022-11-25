package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.PreRegistration
import org.summer.chia.service.PreRegistrationService

@RestController
class CspPreRegistrationController {

    @Autowired
    private lateinit var preRegistrationService: PreRegistrationService

    @PostMapping("/pre/reg")
    fun preRegistration(@RequestBody preRegistration: PreRegistration): Result {
        return preRegistrationService.doPreRegistration(preRegistration)
    }
}
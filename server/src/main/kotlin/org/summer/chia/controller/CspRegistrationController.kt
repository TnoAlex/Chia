package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.RegistrationListItem
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.RegistrationService

@RestController
class CspRegistrationController {
    @Autowired
    private lateinit var registrationService: RegistrationService

    @PostMapping("/teacher/upload/transcripts")
    fun uploadTranscripts(@RequestBody objList: List<RegistrationListItem>): Result {
        return registrationService.transcriptsList(objList)
    }

    @PostMapping("/teacher/upload/regList")
    fun uploadRegistrationList(@RequestBody objList: List<RegistrationListItem>): Result {
        return registrationService.registrationList(objList)
    }

    @GetMapping("/teacher/query/absent/official/{cid}")
    fun queryAbsentOfficialRegistration(@PathVariable cid: String): Result {
        return registrationService.doQueryAbsentOfficialRegistration(cid)
    }

    @GetMapping("/teacher/query/absent/exam/{cid}")
    fun queryAbsentExam(@PathVariable cid: String): Result {
        return registrationService.doQueryAbsentExam(cid)
    }

    @PostMapping("/teacher/notice/official/{cid}")
    fun noticeStudent(
        @RequestBody list: List<String>,
        @PathVariable cid: String,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return registrationService.noticeStudent(list, cid, user)
    }
}
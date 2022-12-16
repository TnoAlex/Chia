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

    @GetMapping("/teacher/query/absent/official/{cid}/{pageNum}/{pageSize}")
    fun queryAbsentOfficialRegistration(
        @PathVariable cid: String, @PathVariable pageNum: String,
        @PathVariable pageSize: String
    ): Result {
        return registrationService.doQueryAbsentOfficialRegistration(cid, pageNum, pageSize)
    }

    @GetMapping("/teacher/query/wrong/{cid}/{pageNum}/{pageSize}")
    fun queryWrongType(
        @PathVariable cid: String,
        @PathVariable pageNum: String,
        @PathVariable pageSize: String
    ): Result {
        return registrationService.queryWrongType(cid, pageNum, pageSize)
    }

    @GetMapping("/teacher/query/official/{cid}/{pageNum}/{pageSize}")
    fun queryOfficialList(
        @PathVariable cid: String,
        @PathVariable pageNum: String,
        @PathVariable pageSize: String
    ): Result {
        return registrationService.queryOfficialList(cid, pageNum, pageSize)
    }

    @GetMapping("/teacher/query/absent/exam/{cid}/{pageNum}/{pageSize}")
    fun queryAbsentExam(
        @PathVariable cid: String,
        @PathVariable pageNum: String,
        @PathVariable pageSize: String
    ): Result {
        return registrationService.doQueryAbsentExam(cid, pageNum, pageSize)
    }


    @PostMapping("/teacher/notice/official/{cid}")
    fun noticeStudent(
        @PathVariable cid: String,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return registrationService.noticeStudent(cid, user)
    }
}
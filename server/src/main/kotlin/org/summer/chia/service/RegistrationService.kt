package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.summer.chia.pojo.ao.RegistrationListItem
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Registration

interface RegistrationService : IService<Registration> {
    fun registrationList(objList: List<RegistrationListItem>): Result
    fun transcriptsList(objList: List<RegistrationListItem>): Result
    fun doQueryAbsentOfficialRegistration(cid: String): Result
    fun doQueryAbsentExam(cid: String): Result
    fun noticeStudent(list: List<String>, cid: String): Result
}
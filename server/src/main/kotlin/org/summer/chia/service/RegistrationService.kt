package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.ao.RegistrationListItem
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Registration

interface RegistrationService : IService<Registration> {
    fun registrationList(objList: List<RegistrationListItem>): Result
    fun transcriptsList(objList: List<RegistrationListItem>): Result
    fun doQueryAbsentOfficialRegistration(cid: String, pageNum: String, pageSize: String): Result
    fun doQueryAbsentExam(cid: String, pageNum: String, pageSize: String): Result
    fun noticeStudent(cid: String, user: UserDetails): Result
    fun queryWrongType(cid: String, pageNum: String, pageSize: String): Result
    fun queryOfficialList(cid: String, pageNum: String, pageSize: String): Result
}
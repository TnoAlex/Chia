package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.ao.PublishCsp
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.CspInfo

interface CspInfoService : IService<CspInfo> {
    fun getAllPreRegistrationBriefInfo(user: UserDetails): Result
    fun addCsp(obj: PublishCsp): Result
    fun modifyCspInfo(obj: PublishCsp): Result
    fun deleteCspInfo(pid: String): Result
}
package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.PreRegistration

interface PreRegistrationService : IService<PreRegistration> {

    fun doPreRegistration(obj: PreRegistration, user: UserDetails): Result
    fun doCancelPreRegistration(cid: String, user: UserDetails): Result
}
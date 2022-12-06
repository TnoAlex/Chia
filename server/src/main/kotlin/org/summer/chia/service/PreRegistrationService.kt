package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.PreRegistration

interface PreRegistrationService : IService<PreRegistration> {

    fun doPreRegistration(obj: PreRegistration): Result
    fun doCancelPreRegistration(pid: String): Result
}
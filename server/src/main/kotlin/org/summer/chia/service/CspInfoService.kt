package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.CspInfo

interface CspInfoService : IService<CspInfo> {
    fun getAllPreRegistrationBriefInfo():Result
}
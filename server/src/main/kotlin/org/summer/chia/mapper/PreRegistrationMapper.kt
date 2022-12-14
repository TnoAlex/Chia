package org.summer.chia.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.ao.CSPPreRegistrationInfo
import org.summer.chia.pojo.dto.PreRegistration

@Mapper
interface PreRegistrationMapper : BaseMapper<PreRegistration> {
    fun queryOne(cid: String, sid: String): CSPPreRegistrationInfo?
}

package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.summer.chia.mapper.CspInfoMapper
import org.summer.chia.pojo.ao.CspBriefInfo
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.CspInfo
import org.summer.chia.service.CspInfoService
import java.time.format.DateTimeFormatter

@Service
class CspInfoServiceImp : ServiceImpl<CspInfoMapper, CspInfo>(), CspInfoService {

    override fun getAllPreRegistrationBriefInfo(): Result {
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val res = ArrayList<CspBriefInfo>()
        baseMapper.selectList(null).forEach {
            val startTime = it.startTime.format(pattern)
            val endTime = it.endTime.format(pattern)
            res.add(CspBriefInfo(it.id!!,it.name.toString(), startTime, endTime, it.preQuantity))
        }
        return Result.success(res)
    }

}
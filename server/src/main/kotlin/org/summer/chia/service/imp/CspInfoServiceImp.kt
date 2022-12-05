package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CspInfoMapper
import org.summer.chia.pojo.ao.CspBriefInfo
import org.summer.chia.pojo.ao.PublishCsp
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.CspInfo
import org.summer.chia.service.CspInfoService
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@Service
class CspInfoServiceImp : ServiceImpl<CspInfoMapper, CspInfo>(), CspInfoService {

    override fun getAllPreRegistrationBriefInfo(): Result {
        val pattern = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val res = ArrayList<CspBriefInfo>()
        baseMapper.selectList(KtQueryWrapper(CspInfo::class.java).orderByDesc(CspInfo::startTime)).forEach {
            val startTime = pattern.format(it.startTime)
            val endTime = pattern.format(it.endTime)
            res.add(CspBriefInfo(it.id!!, it.name.toString(), startTime, endTime, it.preQuantity!!))
        }
        return Result.success(res)
    }

    @Transactional
    override fun addCsp(obj: PublishCsp): Result {
        var state = 0
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val start = Date(format.parse(obj.startTime!!).time)
        if (start < Date())
            state = 1
        val cspInfo = CspInfo(
            null,
            obj.name!!,
            state,
            Timestamp(start.time),
            Timestamp(format.parse(obj.endTime!!).time),
            null,
            null,
            null,
            null,
            null,
        )
        return try {
            baseMapper.insert(cspInfo)
            Result.success()
        } catch (e: Exception) {
            throw SqlException("Insert Exception", this::addCsp.name)
        }
    }

    @Transactional
    override fun modifyCspInfo(obj: PublishCsp): Result {
        val query = KtUpdateWrapper(CspInfo::class.java)
        query.eq(CspInfo::id, obj.id!!)
        obj.name?.let { query.set(CspInfo::name, it) }
        obj.endTime?.let { query.set(CspInfo::endTime, it) }
        obj.startTime?.let { query.set(CspInfo::startTime, it) }
        return try {
            baseMapper.update(null, query)
            Result.success()
        } catch (e: Exception) {
            throw SqlException("Update Exception", this::modifyCspInfo.name)
        }
    }

    @Transactional
    override fun deleteCspInfo(pid: String): Result {
        val csp = baseMapper.selectOne(KtQueryWrapper(CspInfo::class.java).eq(CspInfo::id, pid)) ?: return Result.error(
            "无效的查询id"
        )
        return when (csp.state) {
            0, -1 -> {
                baseMapper.delete(KtQueryWrapper(CspInfo::class.java).eq(CspInfo::id, pid))
                Result.success()
            }

            1 -> Result.error("预报名已开始，无法删除")
            else -> throw SqlException("Unanticipated results", this::deleteCspInfo.name)
        }
    }

}
package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CspInfoMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.CspBriefInfo
import org.summer.chia.pojo.ao.PublishCsp
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.CspInfo
import org.summer.chia.pojo.dto.Student
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.CspInfoService
import org.summer.chia.utils.Log
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@Service
class CspInfoServiceImp : ServiceImpl<CspInfoMapper, CspInfo>(), CspInfoService {

    @Autowired
    private lateinit var studentMapper: StudentMapper

    override fun getAllPreRegistrationBriefInfo(user: UserDetails): Result {
        try {
            val pattern = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val res = ArrayList<CspBriefInfo>()
            val infos = baseMapper.selectList(KtQueryWrapper(CspInfo::class.java).orderByDesc(CspInfo::startTime))
            when ((user as UserDetailsAdapter).getPayLoad()) {
                is Teacher -> {
                    infos.forEach {
                        val startTime = pattern.format(it.startTime)
                        val endTime = pattern.format(it.endTime)
                        res.add(
                            CspBriefInfo(
                                it.id!!,
                                it.name.toString(),
                                startTime,
                                endTime,
                                it.preQuantity!!,
                                it.freeThreshold,
                                null
                            )
                        )
                    }
                }

                is Student -> {
                    val register = studentMapper.queryRegisterList(user.getPayLoad().id!!)
                    infos.forEach {
                        val startTime = pattern.format(it.startTime)
                        val endTime = pattern.format(it.endTime)
                        var tmp = 0
                        if (register.contains(it.id))
                            tmp = 1
                        res.add(
                            CspBriefInfo(
                                it.id!!,
                                it.name.toString(),
                                startTime,
                                endTime,
                                it.preQuantity!!,
                                it.freeThreshold,
                                tmp
                            )
                        )
                    }
                }
            }
            return Result.success(res)
        } catch (e: Exception) {
            Log.error(
                javaClass,
                this::getAllPreRegistrationBriefInfo.name + "-> Query Exception " + e.message,
                e.stackTrace
            )
            throw SqlException("Query Exception", this::getAllPreRegistrationBriefInfo.name)
        }
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
            obj.freeThreshold!!
        )
        return try {
            baseMapper.insert(cspInfo)
            Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::addCsp.name + "-> Insert Exception: " + e.message, e.stackTrace)
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
        obj.freeThreshold?.let { query.set(CspInfo::freeThreshold, it) }
        return try {
            baseMapper.update(null, query)
            Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::modifyCspInfo.name + "-> Update Exception", e.stackTrace)
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
            else -> {
                Log.error(javaClass, this::deleteCspInfo.name + "-> Delete Exception: Unanticipated results", null)
                throw SqlException("Unanticipated results", this::deleteCspInfo.name)
            }
        }
    }

}
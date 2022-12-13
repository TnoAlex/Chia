package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.PreRegistrationMapper
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.PreRegistration
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.PreRegistrationService
import org.summer.chia.utils.Log

@Service
class PreRegistrationServiceImp : ServiceImpl<PreRegistrationMapper, PreRegistration>(), PreRegistrationService {

    @Transactional
    override fun doPreRegistration(obj: PreRegistration, user: UserDetails): Result {
        val account =
            ((user as UserDetailsAdapter).getPayLoad()) as Student
        obj.studentId = account.id
        try {
            return if (baseMapper.selectOne(
                    KtQueryWrapper(PreRegistration::class.java).eq(PreRegistration::studentId, account.id)
                        .eq(PreRegistration::cspId, obj.cspId)
                ) != null
            ) {
                Result.error("已经报名此次CSP认证，无法重复报名")
            } else {
                baseMapper.insert(obj)
                Result.success()
            }
        } catch (e: RuntimeException) {
            Log.error(this.javaClass, this::doPreRegistration.name + " Insertion error", e.suppressed)
            throw SqlException("Insertion error", this::doPreRegistration.name)
        }
    }

    @Transactional
    override fun doCancelPreRegistration(cid: String, user: UserDetails): Result {
        val uid =
            ((user as UserDetailsAdapter).getPayLoad()).id!!
        try {
            val info = baseMapper.queryOne(cid, uid) ?: return Result.error("查询错误")
            return if (info.cspState == -1)
                Result.error("预报名已经结束，无法取消")
            else {
                baseMapper.delete(
                    KtQueryWrapper(PreRegistration::class.java).eq(PreRegistration::cspId, cid)
                        .eq(PreRegistration::studentId, uid)
                )
                Result.success("取消预报名成功")
            }
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::doCancelPreRegistration.name + " An Exception Occurs during query or delete",
                e.suppressed
            )
            throw SqlException("An Exception Occurs during query or delete", this::doCancelPreRegistration.name)
        }
    }
}
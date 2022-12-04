package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.PreRegistrationMapper
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.PreRegistration
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.PreRegistrationService

@Service
class PreRegistrationServiceImp:ServiceImpl<PreRegistrationMapper,PreRegistration>(),PreRegistrationService {

    @Transactional
    override fun doPreRegistration(obj: PreRegistration): Result {
        val account = ((SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).getPayLoad()) as Student
        obj.studentId = account.id
        return try{
            baseMapper.insert(obj)
            Result.success()
        }catch (e:RuntimeException){
            throw  SqlException("Insertion error",this::doPreRegistration.name)
        }
    }

    @Transactional
    override fun doCancelPreRegistration(pid: String): Result {
        val uid  =  ((SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).getPayLoad()).id!!
        try{
            val info = baseMapper.queryOne(pid,uid) ?: return Result.error("查询错误")
            return if(info.cspState == -1)
                Result.error("预报名已经结束，无法取消")
            else{
                baseMapper.delete(KtQueryWrapper(PreRegistration::class.java).eq(PreRegistration::id,pid).eq(PreRegistration::studentId,uid))
                Result.success("取消预报名成功")
            }
        }catch (e:Exception){
            throw SqlException("An Exception Occurs during query or delete",this::doCancelPreRegistration.name)
        }
    }
}
package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.PreRegistrationMapper
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.PreRegistration
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.PreRegistrationService

@Service
class PreRegistrationServiceImp:ServiceImpl<PreRegistrationMapper,PreRegistration>(),PreRegistrationService {

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
}
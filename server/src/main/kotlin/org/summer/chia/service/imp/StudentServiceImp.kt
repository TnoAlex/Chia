package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.dto.Student
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.ao.StudentBriefInfo
import org.summer.chia.service.StudentService

@Service
class StudentServiceImp : ServiceImpl<StudentMapper, Student>(), StudentService {

    override fun loadUserByUsername(username: String): UserDetails? {
        val query = KtQueryWrapper(Student::class.java)
        query.eq(Student::studentNumber, username)
        val res = baseMapper.selectOne(query)
        return if (res != null) {
            res.role = "ROLE_Student"
            UserDetailsAdapter(res)
        } else
            null
    }

    override fun getBriefInfo(): Result {
        val account = (SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).username
        val query = KtQueryWrapper(Student::class.java)
        query.eq(Student::studentNumber,account)
        val res = baseMapper.selectOne(query)
        if (res !=null){
            return  Result.success(StudentBriefInfo(res.name,res.studentNumber,res.maxScore,res.freeTimes))
        }
        else
            throw SqlException("Query Exceptions",this::getBriefInfo.name)
    }


}
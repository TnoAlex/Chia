package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.Student
import org.summer.chia.service.StudentService

@Service
class StudentServiceImp : ServiceImpl<StudentMapper, Student>(), StudentService {

    override fun loadUserByUsername(username: String): UserDetails? {
        val query = KtQueryWrapper(Student::class.java)
        query.eq(Student::studentNumber, username)
        val res = baseMapper.selectOne(query)
        return if (res != null)
            UserDetailsAdapter(res)
        else
            null
    }
}
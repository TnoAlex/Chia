package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.mapper.TeacherMapper
import org.summer.chia.pojo.Teacher
import org.summer.chia.service.TeacherService

@Service
class TeacherServiceImp : ServiceImpl<TeacherMapper, Teacher>(), TeacherService {

    override fun loadUserByUsername(username: String): UserDetails? {
        val query = KtQueryWrapper(Teacher::class.java)
        query.eq(Teacher::email, username)
        val res = baseMapper.selectOne(query)
        return if (res != null)
            UserDetailsAdapter(res)
        else
            null
    }
}
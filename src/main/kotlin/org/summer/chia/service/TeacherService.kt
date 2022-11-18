package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.Teacher

interface TeacherService : IService<Teacher> {
    fun loadUserByUsername(username: String): UserDetails?
}
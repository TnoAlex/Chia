package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.Student

interface StudentService : IService<Student> {
    fun loadUserByUsername(username: String): UserDetails?
}
package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.pojo.ao.Result

interface TeacherService : IService<Teacher> {
    fun loadUserByUsername(username: String): UserDetails?
    fun getUserNameByAccount():Result
}
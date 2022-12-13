package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Teacher

interface TeacherService : IService<Teacher> {
    fun loadUserByUsername(username: String): UserDetails?
    fun getUserNameByAccount(user: UserDetails): Result
    fun addTeacher(obj: Teacher, user: UserDetails): Result
}
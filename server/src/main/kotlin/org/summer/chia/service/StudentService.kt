package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.ao.FreshmanInfo
import org.summer.chia.pojo.dto.Student
import org.summer.chia.pojo.ao.Result

interface StudentService : IService<Student> {
    fun loadUserByUsername(username: String): UserDetails?
    fun getBriefInfo():Result
    fun enableAccount(mailAddress:String):Result
    fun importStudent(infos:List<FreshmanInfo>):Result
}
package org.summer.chia.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.User

interface UserService : UserDetailsService {
    fun resetUserPassword(obj: RestPassword): Result
}
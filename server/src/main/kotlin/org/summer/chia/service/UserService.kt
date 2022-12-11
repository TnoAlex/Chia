package org.summer.chia.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result

interface UserService : UserDetailsService {
    fun resetUserPassword(obj: RestPassword, user: UserDetails): Result
}
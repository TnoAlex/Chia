package org.summer.chia.adapter

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.summer.chia.pojo.dto.User

class UserDetailsAdapter(private val user: User) : UserDetails {

    fun getPayLoad() = user

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (user.role.isNullOrBlank()) {
            throw RuntimeException("空权限错误")
        }
        return mutableListOf(SimpleGrantedAuthority(user.role))
    }

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.name

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = true
}
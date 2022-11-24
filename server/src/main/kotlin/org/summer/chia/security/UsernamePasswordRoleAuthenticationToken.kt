package org.summer.chia.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class UsernamePasswordRoleAuthenticationToken(principal: Any, credentials: Any, val roleType: Any) :
    UsernamePasswordAuthenticationToken(principal, credentials) {

}
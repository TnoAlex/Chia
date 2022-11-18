package org.summer.chia.exception

import org.springframework.security.core.AuthenticationException

class RoleTypeErrorException(message: String) : AuthenticationException(message)
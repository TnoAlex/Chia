package org.summer.chia.pojo.ao


enum class ResultStatus(var value: Int, var message: String) {
    OK(200, "OK"),
    BAD_AUTHENTICATION(401, "Illegal credentials"),
    NONE_AUTHENTICATION(403, "Full authentication is required to access this resource"),
    ACCESS_DENIED(403, "Unauthorized access")
}
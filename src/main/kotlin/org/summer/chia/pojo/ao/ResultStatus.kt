package org.summer.chia.pojo.ao


enum class ResultStatus(var value: Int, var message: String) {
    OK(200, "OK"),
    BAD_AUTHENTICATION(401, "Illegal credentials")
}
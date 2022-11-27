package org.summer.chia.handler

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.summer.chia.exception.ServiceConfigException
import org.summer.chia.exception.SqlException

@ControllerAdvice
class ServiceExceptionHandler {
    @ExceptionHandler(SqlException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handlerSqlException(e: SqlException): HashMap<String, Any> {
        return commonHandle(e.message!!,e.causedBy)
    }

    @ExceptionHandler(ServiceConfigException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handlerServiceConfigException(e: ServiceConfigException): HashMap<String, Any> {
        return commonHandle(e.message!!,e.causedBy)
    }

    private fun commonHandle(msg: String, causedBy: String): HashMap<String, Any> {
        val res = HashMap<String, Any>()
        res["code"] = HttpStatus.INTERNAL_SERVER_ERROR
        res["msg"] = msg
        res["data"] = "caused by$causedBy"
        return res
    }
}
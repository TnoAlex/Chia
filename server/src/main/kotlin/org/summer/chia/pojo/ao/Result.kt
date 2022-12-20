package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class Result(
    @Schema(description = "返回数据")
    var data: Any?,
    @Schema(description = "返回消息")
    var msg: String?,
    @Schema(description = "返回状态")
    var code: Int
) {
    companion object {
        fun success(): Result {
            return Result(null, "ok", 200)
        }

        fun success(obj: Any): Result {
            return Result(obj, "ok", 200)
        }

        fun success(message: String): Result {
            return Result(null, message, 200)
        }

        fun error(status: ResultStatus): Result {
            return Result(null, status.message, status.value)
        }

        fun error(status: ResultStatus, obj: Any): Result {
            return Result(obj, status.message, status.value)
        }

        fun error(message: String): Result {
            return Result(null, message, 403)
        }
    }

}

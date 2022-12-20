package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class RestPassword(
    @Schema(description = "新密码")
    var newPassword: String,
    @Schema(description = "重置密码验证码")
    var code: String,
    @Schema(description = "账号类型", example = "1")
    var type: Int
)

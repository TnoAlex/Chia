package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class CommunicativeTarget(
    @Schema(description = "老师id")
    var id: String,
    @Schema(description = "老师姓名")
    var name: String
)

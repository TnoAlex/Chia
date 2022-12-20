package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class RegistrationListItem(
    @Schema(description = "CSP ID")
    var cspId: String,
    @Schema(description = "学生学号")
    var studentIdNumber: String,
    @Schema(description = "报名类型")
    var type: Int,
    @Schema(description = "CSP 成绩")
    var score: Int?
)

package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class CSPPreRegistrationInfo(
    @Schema(description = "CSP ID")
    var cspId: String,
    @Schema(description = "CSP 届次", example = "33")
    var cspName: Int,
    @Schema(description = "CSP 状态", example = "0")
    var cspState: Int,
    @Schema(description = "学生 ID")
    var studentId: String,
    @Schema(description = "学生姓名")
    var studentName: String,
    @Schema(description = "学生学号")
    var studentNumber: String,
)

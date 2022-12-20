package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class WrongTypeReg(
    @Schema(description = "学生姓名")
    var name: String,
    @Schema(description = "学生学号")
    var studentNumber: String,
    @Schema(description = "学生年级")
    var grade: String,
    @Schema(description = "错误类型", example = "自费错报免费团")
    var type: String
)

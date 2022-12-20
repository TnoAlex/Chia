package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class FreshmanInfo(
    @Schema(description = "学生姓名")
    var name: String,
    @Schema(description = "学生学号")
    var studentNumber: String,
    @Schema(description = "学生身份证号")
    var idNumber: String,
    @Schema(description = "免费参加CSP的次数")
    var freeTime: Int,
    @Schema(description = "入学时间", example = "2022-10-11")
    var enrollmentTime: String
)

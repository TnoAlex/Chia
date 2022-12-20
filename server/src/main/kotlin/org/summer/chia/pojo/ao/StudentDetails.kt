package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class StudentDetails(
    @Schema(description = "学生姓名")
    var name: String,
    @Schema(description = "学生学号")
    var studentID: String,
    @Schema(description = "学生身份证号")
    var idNumber: String,
    @Schema(description = "学生最高CSP成绩")
    var maxScore: Int,
    @Schema(description = "免费次数")
    var freeTime: Int,
    @Schema(description = "账号状态")
    var status: Int,
    @Schema(description = "学生邮箱")
    var email: String
)

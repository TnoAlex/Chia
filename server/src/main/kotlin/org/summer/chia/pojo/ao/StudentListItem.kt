package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class StudentListItem(
    @Schema(description = "学生ID")
    var id: String,
    @Schema(description = "学生姓名")
    var name: String,
    @Schema(description = "学生学号")
    var studentNumber: String,
    @Schema(description = "学生最高CSP成绩")
    var maxScore: Int,
    @Schema(description = "CSP免费次数")
    var freeTime: Int,
    @Schema(description = "学生年级")
    var grade: String,
    @Schema(description = "学生总数")
    var totalSize: Long?,
    @Schema(description = "预报名附加消息")
    var extra: String?
)

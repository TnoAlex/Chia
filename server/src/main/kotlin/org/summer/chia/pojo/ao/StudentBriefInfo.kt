package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class StudentBriefInfo(
    @Schema(description = "学生姓名")
    var name:String,
    @Schema(description = "学生学号")
    var studentNum:String,
    @Schema(description = "最高CSP成绩")
    var maxScore:Int,
    @Schema(description = "CSP免费次数")
    var freeTime:Int,
    @Schema(description = "账号状态")
    var status:Int
)

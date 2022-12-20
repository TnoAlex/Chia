package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class CspBriefInfo(
    @Schema(description = "CSP ID")
    var id: String,
    @Schema(description = "CSP 届次", example = "33")
    var name: String,
    @Schema(description = "开始时间", example = "2022-10-11 12:30:12")
    var startTime: String,
    @Schema(description = "结束时间", example = "2022-11-12 16:45:00")
    var endTime: String,
    @Schema(description = "预报名参与人数")
    var personNumber: Int,
    @Schema(description = "获取免费次数最低分数")
    var freeThreshold: Int,
    @Schema(description = "是否已报名")
    var registered: Int?
)

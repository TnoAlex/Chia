package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@Schema(description = "CSP 发布与修改")
@NoArg
data class PublishCsp(
    @Schema(description = "CSP ID")
    var id: String?,
    @Schema(description = "CSP 届次")
    var name: Int?,
    @Schema(description = "CSP 预报名开始时间", example = "2022-10-19 9:00:10")
    var startTime: String?,
    @Schema(description = "CSP 预报名结束时间", example = "2022-10-19 9:00:10")
    var endTime: String?,
    @Schema(description = "CSP 获取免费次数最低分数")
    var freeThreshold: Int?
)

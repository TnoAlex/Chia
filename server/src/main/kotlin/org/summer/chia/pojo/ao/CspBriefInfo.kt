package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class CspBriefInfo(
    var id: String,
    var name: String,
    var startTime: String,
    var endTime: String,
    var personNumber: Int,
    var freeThreshold: Int,
    var registered: Int?
)

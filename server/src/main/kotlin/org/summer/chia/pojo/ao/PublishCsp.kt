package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class PublishCsp(
    var id: String?,
    var name: Int?,
    var startTime: String?,
    var endTime: String?
)

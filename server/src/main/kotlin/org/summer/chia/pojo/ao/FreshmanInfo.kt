package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class FreshmanInfo(
    var name: String,
    var studentNumber: String,
    var idNumber: String,
    var freeTime: Int,
    var enrollmentTime: String
)

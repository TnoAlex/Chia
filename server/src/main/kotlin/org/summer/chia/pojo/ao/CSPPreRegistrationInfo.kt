package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class CSPPreRegistrationInfo(
    var cspId: String,
    var cspName: Int,
    var cspState: Int,
    var studentId: String,
    var studentName: String,
    var studentNumber: String,
)

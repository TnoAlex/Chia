package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class WrongTypeReg(
    var name: String,
    var studentNumber: String,
    var grade: String,
    var type: String
)

package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class MessageObject(
    var reciId: String,
    var content: String
)

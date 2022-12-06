package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class MessageListItem(
    var id: String,
    var sendForm: String,
    var sendTo: String,
    var content: String,
    var status: Int
)

package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class MessageListItem(
    var id: String,
    var sendFrom: String,
    var senFromId: String,
    var sendTo: String,
    var sendToId: String,
    var content: String,
    var status: Int,
    var totalSize: Int
)

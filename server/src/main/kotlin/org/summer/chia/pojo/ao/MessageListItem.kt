package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class MessageListItem(
    @Schema(description = "消息ID")
    var id: String,
    @Schema(description = "消息发送方")
    var sendFrom: String,
    @Schema(description = "消息发送方ID")
    var sendFromId: String,
    @Schema(description = "消息接收方")
    var sendTo: String,
    @Schema(description = "消息接收方ID")
    var sendToId: String,
    @Schema(description = "消息内容")
    var content: String,
    @Schema(description = "消息状态")
    var status: Int,
    @Schema(description = "消息总数")
    var totalSize: Int?
)

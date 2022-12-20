package org.summer.chia.pojo.ao

import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
data class MessageObject(
    @Schema(description = "消息接收方ID")
    var reciId: String,
    @Schema(description = "消息内容")
    var content: String
)

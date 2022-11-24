package org.summer.chia.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg

@NoArg
@TableName("message")
data class Message(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var time: java.time.LocalDateTime,
    var content: String,
    var deliId: String,
    var reciId: String,
    var deliType: Int,

    )

package org.summer.chia.pojo.dto

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg
import java.sql.Timestamp

@NoArg
@TableName("message")
data class Message(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String?,
    var time: Timestamp,
    var content: String,
    var deliId: String,
    var reciId: String,
    var status: Int?
)

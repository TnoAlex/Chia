package org.summer.chia.pojo.dto

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg
import java.sql.Timestamp

@NoArg
@TableName("csp_info")
data class CspInfo(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String?,
    var name: Int,
    var state: Int,
    var startTime: Timestamp,
    var endTime: Timestamp,
    var preQuantity: Int?,
    var quantity: Int?,
    var scoreH: Int?,
    var scoreA: Int?,
    var stage: Int?,
    var freeThreshold: Int
)

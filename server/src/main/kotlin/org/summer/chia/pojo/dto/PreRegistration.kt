package org.summer.chia.pojo.dto

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg

@NoArg
@TableName("pre_registration")
data class PreRegistration(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String?,
    var cspId: String,
    var studentId: String?,
    var type: Int,
    var extra: String,
    )

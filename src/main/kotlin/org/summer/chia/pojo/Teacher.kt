package org.summer.chia.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg

@NoArg
@TableName("teacher")
data class Teacher(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var name: String,
    var password: String,
)

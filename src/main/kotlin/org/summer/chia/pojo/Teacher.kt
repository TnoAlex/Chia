package org.summer.chia.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg

@NoArg
@TableName("teacher")
data class Teacher(
    @TableId(type = IdType.ASSIGN_ID)
    override var id: String?,
    override var name: String,
    override var password: String,
    var email: String,
    @TableField(exist = false)
    override var role: String?
) : User(id, name, password, null)

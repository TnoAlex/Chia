package org.summer.chia.pojo.dto

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg
import java.sql.Date

@NoArg
@TableName("student")
data class Student(
    @TableId(type = IdType.ASSIGN_ID)
    override var id: String?,
    override var name: String,
    override var password: String,
    var studentNumber: String,
    var idNumber: String,
    var maxScore: Int,
    var freeTimes: Int,
    override var email: String?,
    var status:Int,
    var enrollmentTime: Date,
    @TableField(exist = false)
    override var role: String?
) : User(id, name, password, email,null)

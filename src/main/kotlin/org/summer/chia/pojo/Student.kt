package org.summer.chia.pojo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.summer.chia.annotation.NoArg

@NoArg
@TableName("student")
data class Student(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var name: String,
    var password: String,
    var studentNumber: String,
    var idNumber: String,
    var maxScore: Int,
    var freeTimes: Int,
    var email: String,
    var enrollmentTime: java.time.LocalDateTime,
) : User(id, name, password, null)

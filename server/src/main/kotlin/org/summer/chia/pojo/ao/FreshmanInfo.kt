package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg
import java.sql.Date

@NoArg
data class FreshmanInfo(
    var name:String,
    var studentNumber: String,
    var idNumber:String,
    var enrollmentTime:String
)

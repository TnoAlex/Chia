package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class StudentBriefInfo(
    var name:String,
    var studentNum:String,
    var maxScore:Int,
    var freeTime:Int
)

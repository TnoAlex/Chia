package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class StudentListItem(
    var name:String,
    var studentNum:String,
    var maxScore:Int,
    var freeTime:Int,
    var grade:String
)

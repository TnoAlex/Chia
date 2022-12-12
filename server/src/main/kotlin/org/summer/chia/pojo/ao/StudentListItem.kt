package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class StudentListItem(
    var id: String,
    var name: String,
    var studentNumber: String,
    var maxScore: Int,
    var freeTime: Int,
    var grade: String,
    var totalSize: Long?
)

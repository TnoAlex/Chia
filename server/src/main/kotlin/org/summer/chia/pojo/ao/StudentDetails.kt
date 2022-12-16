package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class StudentDetails(
    var name: String,
    var studentID: String,
    var idNumber: String,
    var maxScore: Int,
    var freeTime: Int,
    var status: Int,
    var email: String
)

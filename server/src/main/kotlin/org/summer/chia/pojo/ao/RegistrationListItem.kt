package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class RegistrationListItem(
    var cspId: String,
    var studentIdNumber: String,
    var type: Int,
    var socre: Int?
)

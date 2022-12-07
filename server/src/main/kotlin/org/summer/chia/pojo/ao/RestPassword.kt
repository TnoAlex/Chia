package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class RestPassword(
    var newPassword: String,
    var code: String,
    var type: Int
)

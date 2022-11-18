package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class LoginObject(
    var username: String,
    var password: String,
    var type: Int
)

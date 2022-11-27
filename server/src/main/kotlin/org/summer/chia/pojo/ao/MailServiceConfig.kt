package org.summer.chia.pojo.ao

import org.summer.chia.annotation.NoArg

@NoArg
data class MailServiceConfig(
    var mailUser: String,
    var mailAuthorizationCode: String,
    var mailHost: String
)

package org.summer.chia.service

import org.summer.chia.pojo.ao.Result

interface MailConfigService {
    fun testMailConfig():Result
}
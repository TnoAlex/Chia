package org.summer.chia.service

import org.summer.chia.pojo.ao.MailServiceConfig
import org.summer.chia.pojo.ao.Result

interface MailConfigService {
    fun initMailConfig(config: MailServiceConfig):Result
    fun testMailConfig():Result
}
package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.pojo.ao.MailServiceConfig
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.MailConfigService

@RestController
class SettingController {

    @Autowired
    private lateinit var mailConfigService: MailConfigService

    @PostMapping("/sys/setting/mail_init")
    fun initMailSenderConfig(@RequestBody config : MailServiceConfig): Result {
        return mailConfigService.initMailConfig(config)
    }

    @PostMapping("/sys/setting/mail_test")
    fun testMailSetting():Result {
        return  mailConfigService.testMailConfig()
    }
}
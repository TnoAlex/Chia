package org.summer.chia.service.imp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mail.MailProperties
import org.springframework.stereotype.Service
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.MailConfigService
import org.summer.chia.utils.Log
import org.summer.chia.utils.TelnetUtil


@Service
class MailConfigServiceImp : MailConfigService {

    @Autowired
    private lateinit var mailConfig: MailProperties

    override fun testMailConfig(): Result {
        try {
            TelnetUtil.testMailConnection(mailConfig.host, mailConfig.username, mailConfig.password)
        } catch (e: Exception) {
            Log.error(javaClass, this::testMailConfig.name + "-> Mail Setting Exception: " + e.message, e.suppressed)
            throw e
        }
        return Result.success()
    }
}
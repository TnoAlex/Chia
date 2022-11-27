package org.summer.chia.service.imp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.refresh.ContextRefresher
import org.springframework.stereotype.Service
import org.summer.chia.exception.ServiceConfigException
import org.summer.chia.pojo.ao.MailServiceConfig
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.MailConfigService
import org.summer.chia.utils.TelnetUtil
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter


@Service
class MailConfigServiceImp : MailConfigService {

    @Qualifier("configDataContextRefresher")
    @Autowired
    private lateinit var contextRefresher: ContextRefresher

    @Value("\${spring.mail.host}")
    private lateinit var host: String

    @Value("\${spring.mail.username}")
    private lateinit var username: String

    @Value("\${spring.mail.password}")
    private lateinit var password: String

    override fun initMailConfig(config: MailServiceConfig): Result {
        try {
            val configPath = this::class.java.classLoader.getResource("application.yml")!!.path
            val yaml = Yaml()
            val stream = FileInputStream(configPath)
            val params = yaml.load<Map<String, Any>>(stream)
            val mailParams = ((params["spring"] as Map<*, *>)["mail"] as Map<*, *>).toMutableMap()
            mailParams["host"] = config.mailHost
            mailParams["username"] = config.mailUser
            mailParams["password"] = config.mailAuthorizationCode
            val writer = FileWriter(File(configPath))
            writer.write(yaml.dumpAsMap(params))
            writer.flush()
            writer.close()
            stream.close()
            contextRefresher.refresh()
            return Result.success()
        } catch (e: Exception) {
            throw ServiceConfigException("An exception occurred in the system configuration", this::initMailConfig.name)
        }
    }

    override fun testMailConfig(): Result {
        try {
            TelnetUtil.testMailConnection(host, username, password)
        } catch (e: Exception) {
            throw e
        }
        return Result.success()
    }
}
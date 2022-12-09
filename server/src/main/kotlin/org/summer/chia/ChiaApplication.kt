package org.summer.chia

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.summer.chia.mapper.TeacherMapper
import org.summer.chia.pojo.ao.MailServiceConfig
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.MailConfigService
import org.summer.chia.utils.Log
import java.util.*

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
//class ChiaApplication

class ChiaApplication : ApplicationRunner {

    @Autowired
    private lateinit var mailServiceConfig: MailConfigService

    @Autowired
    private lateinit var teacherMapper: TeacherMapper

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    override fun run(args: ApplicationArguments) {
        Log.info(
            javaClass,
            "----------------------------------------初始化开始--------------------------------------",
            null
        )
        if (!args.containsOption("ea") || !args.containsOption("ep") || !args.containsOption("eh")) {
            Log.error(this.javaClass, "初始化邮箱账户和密码未提供", null)
            SpringApplication.exit(applicationContext)
        } else {
            try {
                var res = mailServiceConfig.initMailConfig(
                    MailServiceConfig(
                        args.getOptionValues("ea")[0],
                        args.getOptionValues("ep")[0],
                        args.getOptionValues("eh")[0]
                    )
                )
                if (res.code != 200) {
                    Log.error(this.javaClass, "初始化进程错误，邮箱无法设置", null)
                    SpringApplication.exit(applicationContext)
                }
                res = mailServiceConfig.testMailConfig()
                if (res.code != 200) {
                    Log.error(this.javaClass, "初始化进程错误，邮箱无效", null)
                    SpringApplication.exit(applicationContext)
                }
                Log.info(this.javaClass, "邮件模块初始化成功", null)

                val systemAccount =
                    teacherMapper.selectOne(KtQueryWrapper(Teacher::class.java).eq(Teacher::id, "System"))
                if (systemAccount == null) {
                    teacherMapper.insert(Teacher("System", "System", UUID.randomUUID().toString(), "System", null))
                }
                Log.info(this.javaClass, "数据库初始化成功", null)
                Log.info(
                    javaClass,
                    "----------------------------------------初始化结束--------------------------------------",
                    null
                )
            } catch (e: Exception) {
                Log.error(this.javaClass, "初始化进程错误", e.suppressed)
                SpringApplication.exit(applicationContext)
            }
        }
    }

}

fun main(args: Array<String>) {
    runApplication<ChiaApplication>(*args)
}

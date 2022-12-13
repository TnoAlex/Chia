package org.summer.chia

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.summer.chia.mapper.TeacherMapper
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.MailConfigService
import org.summer.chia.utils.Log
import java.util.*

@SpringBootApplication
@EnableTransactionManagement
//class ChiaApplication

class ChiaApplication : ApplicationRunner {

    @Autowired
    private lateinit var mailServiceConfig: MailConfigService

    @Autowired
    private lateinit var teacherMapper: TeacherMapper

    @Autowired
    private lateinit var applicationContext: ApplicationContext


    override fun run(args: ApplicationArguments) {
        Log.info(javaClass, "---------------------------------初始化进程启动-------------------------", null)
        try {
            val res = mailServiceConfig.testMailConfig()
            if (res.code != 200) {
                Log.error(this.javaClass, "初始化进程错误，邮箱无效", null)
                SpringApplication.exit(applicationContext)
            }
            Log.info(this.javaClass, "邮件模块初始化成功", null)
            val accounts = teacherMapper.selectList(null)
            if (accounts.isNullOrEmpty()) {
                val scanner = Scanner(System.`in`)
                Log.info(javaClass, "请输入初始化教师姓名", null)
                val teacherName = scanner.next()
                Log.info(javaClass, "请输入此用户密码", null)
                val teacherPassword = scanner.next()
                Log.info(javaClass, "请输入此用户邮箱", null)
                val teacherEmail = scanner.next()
                teacherMapper.insert(
                    Teacher(
                        null,
                        teacherName,
                        BCryptPasswordEncoder().encode(teacherPassword),
                        teacherEmail,
                        null
                    )
                )
            }
            val systemAccount =
                teacherMapper.selectOne(KtQueryWrapper(Teacher::class.java).eq(Teacher::id, "System"))
            if (systemAccount == null) {
                teacherMapper.insert(Teacher("System", "System", UUID.randomUUID().toString(), "System", null))
            }
            Log.info(this.javaClass, "数据库初始化成功", null)
            Log.info(javaClass, "---------------------------------初始化进程完成-------------------------", null)
        } catch (e: Exception) {
            Log.error(this.javaClass, "初始化进程错误:" + e.message, e.suppressed)
            SpringApplication.exit(applicationContext)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<ChiaApplication>(*args)
}

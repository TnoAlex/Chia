package org.summer.chia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
class ChiaApplication
//class ChiaApplication:ApplicationRunner{
//
//    @Autowired
//    private lateinit var mailServiceConfig: MailConfigService
//
//    @Autowired
//    private lateinit var teacherMapper: TeacherMapper
//
//    override fun run(args: ApplicationArguments) {
//        TODO("Not yet implemented")
//    }
//
//}

fun main(args: Array<String>) {
    runApplication<ChiaApplication>(*args)
}

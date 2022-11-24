package org.summer.chia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class ChiaApplication

fun main(args: Array<String>) {
    runApplication<ChiaApplication>(*args)
}

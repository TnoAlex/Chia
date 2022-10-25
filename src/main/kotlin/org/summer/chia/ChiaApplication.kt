package org.summer.chia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChiaApplication

fun main(args: Array<String>) {
    runApplication<ChiaApplication>(*args)
}

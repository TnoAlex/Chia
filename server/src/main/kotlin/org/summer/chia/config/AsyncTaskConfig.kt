package org.summer.chia.config

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.summer.chia.exception.MailSendException
import java.lang.reflect.Method
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor


@Configuration
@EnableAsync
class AsyncTaskConfig : AsyncConfigurer {

    override fun getAsyncExecutor(): Executor? {
        val taskPool = ThreadPoolTaskExecutor()
        taskPool.corePoolSize = 5
        taskPool.maxPoolSize = 50
        taskPool.queueCapacity = 1000
        taskPool.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        taskPool.setThreadNamePrefix("mail-send-thread")
        taskPool.initialize()
        return taskPool
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
        return MailSendAsyncUncaughtExceptionHandler()
    }

    inner class MailSendAsyncUncaughtExceptionHandler : AsyncUncaughtExceptionHandler {
        override fun handleUncaughtException(ex: Throwable, method: Method, vararg params: Any?) {
            throw MailSendException("Mail send error", method.name)
        }
    }
}
package org.summer.chia.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine

@Component
class MailSendUtil {

    @Autowired
    private lateinit var javaMailSender: JavaMailSender
    @Autowired
    private lateinit var templateEngine: TemplateEngine
}
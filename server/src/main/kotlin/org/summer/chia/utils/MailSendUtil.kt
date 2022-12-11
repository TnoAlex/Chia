package org.summer.chia.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.summer.chia.exception.MailSendException
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Component
@EnableAspectJAutoProxy(exposeProxy = true)
class MailSendUtil {

    @Value("\${spring.mail.username}")
    private lateinit var mailFrom: String

    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    @Autowired
    private lateinit var templateEngine: TemplateEngine

    fun sendTemplateMail(receiver: String, subject: String, emailTemplate: String, dataMap: Map<String, Any>) {
        try{
            val context = Context()
            for (i in dataMap.keys) {
                context.setVariable(i, dataMap[i])
            }
            val  templateContent = templateEngine.process(emailTemplate, context)
            val message = javaMailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true)
            helper.setFrom(mailFrom)
            helper.setTo(receiver)
            helper.setSubject(subject)
            helper.setText(templateContent, true)
            javaMailSender.send(message)
        }catch (_ :Exception){
            throw MailSendException("Mail Send Failure",this::sendTemplateMail.name)
        }
    }
}
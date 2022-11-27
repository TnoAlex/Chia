package org.summer.chia.exception

class MailSendException(message: String, val causedBy: String) : RuntimeException(message)
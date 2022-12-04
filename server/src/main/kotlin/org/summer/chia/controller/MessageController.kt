package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.summer.chia.service.MessageService

@RestController
class MessageController {
    @Autowired
    private lateinit var messageService: MessageService
}
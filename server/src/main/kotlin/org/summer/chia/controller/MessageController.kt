package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.MessageObject
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.MessageService

@RestController
class MessageController {
    @Autowired
    private lateinit var messageService: MessageService

    @PostMapping("/message/post")
    fun postMessage(@RequestBody message: MessageObject): Result {
        return messageService.doPostMessage(message)
    }

    @GetMapping("/message/query/communicative")
    fun queryCommunicative(): Result {
        return messageService.doQueryCommunicative()
    }

    @GetMapping("/message/query/list/{uid}")
    fun queryMessageList(@PathVariable uid: String): Result {
        return messageService.doQueryMessageList(uid)
    }

    @PostMapping("/message/read")
    fun readMessage(@RequestBody list: List<String>): Result {
        return messageService.markMessage(list)
    }

    @PostMapping("/message/delete")
    fun deleteMessage(@RequestBody list: List<String>): Result {
        return messageService.deleteMessage(list)
    }
}
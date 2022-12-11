package org.summer.chia.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.MessageObject
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.MessageService

@RestController
class MessageController {
    @Autowired
    private lateinit var messageService: MessageService

    @PostMapping("/message/post")
    fun postMessage(@RequestBody message: MessageObject, @AuthenticationPrincipal user: UserDetails): Result {
        return messageService.doPostMessage(message, user)
    }

    @GetMapping("/message/query/communicative")
    fun queryCommunicative(): Result {
        return messageService.doQueryCommunicative()
    }

    @GetMapping("/message/query/list/{pageNum}/{pageSize}")
    fun queryMessageList(
        @PathVariable pageSize: String,
        @PathVariable pageNum: String,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return messageService.doQueryMessageList(pageNum, pageSize, user)
    }

    @GetMapping("/teacher/message/list/{pageNum}/{pageSize}")
    fun querySystemMessage(
        @PathVariable pageNum: String,
        @PathVariable pageSize: String,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return messageService.doQuerySystemMessage(pageNum, pageSize, user)
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
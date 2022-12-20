package org.summer.chia.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.summer.chia.pojo.ao.CommunicativeTarget
import org.summer.chia.pojo.ao.MessageObject
import org.summer.chia.pojo.ao.Result
import org.summer.chia.service.MessageService

@Tag(name = "消息模块")
@RestController
class MessageController {
    @Autowired
    private lateinit var messageService: MessageService


    @Operation(summary = "发送消息")
    @Parameters(
        Parameter(name = "message", description = "消息实体", required = true),
        Parameter(name = "user", required = true, hidden = true)
    )
    @PostMapping("/message/post")
    fun postMessage(@RequestBody message: MessageObject, @AuthenticationPrincipal user: UserDetails): Result {
        return messageService.doPostMessage(message, user)
    }

    @Operation(summary = "获取老师列表")
    @ApiResponse(
        responseCode = "200", description = "老师列表", content = [Content(
            schema = Schema(implementation = CommunicativeTarget::class)
        )]
    )
    @GetMapping("/message/query/communicative")
    fun queryCommunicative(): Result {
        return messageService.doQueryCommunicative()
    }

    @Operation(summary = "获取消息列表")
    @Parameters(
        Parameter(name = "cid", description = "查询的CSP ID", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @GetMapping("/message/query/list/{pageNum}/{pageSize}")
    fun queryMessageList(
        @PathVariable pageSize: String,
        @PathVariable pageNum: String,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return messageService.doQueryMessageList(pageNum, pageSize, user)
    }

    @Operation(summary = "获取缺系统消息列表")
    @Parameters(
        Parameter(name = "cid", description = "查询的CSP ID", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageNum", description = "查询当前页面索引", required = true, `in` = ParameterIn.PATH),
        Parameter(name = "pageSize", description = "最大页面大小", required = true, `in` = ParameterIn.PATH)
    )
    @GetMapping("/teacher/message/list/{pageNum}/{pageSize}")
    fun querySystemMessage(
        @PathVariable pageNum: String,
        @PathVariable pageSize: String,
        @AuthenticationPrincipal user: UserDetails
    ): Result {
        return messageService.doQuerySystemMessage(pageNum, pageSize, user)
    }

    @Operation(summary = "标记已读")
    @Parameters(
        Parameter(name = "list", description = "已读消息的id列表", required = true),
    )
    @PostMapping("/message/read")
    fun readMessage(@RequestBody list: List<String>): Result {
        return messageService.markMessage(list)
    }

    @Operation(summary = "删除消息")
    @Parameters(
        Parameter(name = "list", description = "删除消息的id列表", required = true),
    )
    @PostMapping("/message/delete")
    fun deleteMessage(@RequestBody list: List<String>): Result {
        return messageService.deleteMessage(list)
    }
}
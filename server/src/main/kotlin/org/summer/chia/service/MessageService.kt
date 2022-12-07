package org.summer.chia.service

import com.baomidou.mybatisplus.extension.service.IService
import org.summer.chia.pojo.ao.MessageObject
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Message

interface MessageService : IService<Message> {
    fun doPostMessage(message: MessageObject): Result
    fun doQueryCommunicative(): Result
    fun doQueryMessageList(uid: String): Result
    fun markMessage(list: List<String>): Result
    fun deleteMessage(list: List<String>): Result
}
package org.summer.chia.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.ao.MessageListItem
import org.summer.chia.pojo.dto.Message

@Mapper
interface MessageMapper : BaseMapper<Message> {
    fun queryStudentMessage(page: Page<MessageListItem>, sid: String): Page<MessageListItem>
    fun queryStudentMessageNumber(sid: String): Long
    fun queryTeacherMessage(page: Page<MessageListItem>, tid: String): Page<MessageListItem>
    fun queryTeacherMessageNumber(tid: String): Long
    fun querySystemMessage(page: Page<MessageListItem>, tid: String): Page<MessageListItem>
    fun querySystemMessageNumber(tid: String): Long
}

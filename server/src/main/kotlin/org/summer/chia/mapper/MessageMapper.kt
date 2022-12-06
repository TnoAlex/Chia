package org.summer.chia.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.ao.MessageListItem
import org.summer.chia.pojo.dto.Message

@Mapper
interface MessageMapper : BaseMapper<Message> {
    fun queryStudentMessage(sid: String): List<MessageListItem>
    fun queryTeacherMessage(tid: String): List<MessageListItem>
    fun querySystemMessage(tid: String): List<MessageListItem>
}

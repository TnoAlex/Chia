package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.summer.chia.mapper.MessageMapper
import org.summer.chia.pojo.dto.Message
import org.summer.chia.service.MessageService

@Service
class MessageServiceImp : ServiceImpl<MessageMapper, Message>(), MessageService {
}
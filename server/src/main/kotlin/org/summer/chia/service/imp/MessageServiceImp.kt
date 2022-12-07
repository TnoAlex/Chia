package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.MessageMapper
import org.summer.chia.mapper.TeacherMapper
import org.summer.chia.pojo.ao.CommunicativeTarget
import org.summer.chia.pojo.ao.MessageObject
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Message
import org.summer.chia.pojo.dto.Student
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.MessageService
import org.summer.chia.utils.Log
import java.sql.Timestamp

@Service
class MessageServiceImp : ServiceImpl<MessageMapper, Message>(), MessageService {

    @Autowired
    private lateinit var teacherMapper: TeacherMapper

    @Transactional
    override fun doPostMessage(message: MessageObject): Result {
        try {
            baseMapper.insert(
                Message(
                    null,
                    Timestamp(System.currentTimeMillis()),
                    message.content,
                    message.deliId,
                    message.reciId,
                    null
                )
            )
            return Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::doPostMessage.name + " Insert Exception", e.suppressed)
            throw SqlException("Insert Exception", this::doPostMessage.name)
        }
    }

    override fun doQueryCommunicative(): Result {
        try {
            val target = teacherMapper.selectList(null) ?: return Result.error("暂时无可联系的老师")
            val res = target.map {
                CommunicativeTarget(it.id!!, it.name)
            }
            return Result.success(res)
        } catch (e: Exception) {
            Log.error(this.javaClass, this::doQueryCommunicative.name + " Query Exception", e.suppressed)
            throw SqlException("Query Exception", this::doQueryCommunicative.name)
        }
    }

    override fun doQueryMessageList(uid: String): Result {
        val user = (SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).getPayLoad()
        try {
            return when (user) {
                is Student -> {
                    val res = baseMapper.queryStudentMessage(user.id!!)
                    Result.success(res)
                }

                is Teacher -> {
                    val res = baseMapper.queryTeacherMessage(user.id!!)
                    val systemMessage = baseMapper.querySystemMessage(user.id!!)
                    res.toMutableList().addAll(systemMessage)
                    Result.success(res)
                }

                else -> {
                    Result.error("无效的用户")
                }
            }
        } catch (e: Exception) {
            Log.error(this.javaClass, this::doQueryMessageList.name + " Query Exception", e.suppressed)
            throw SqlException("Query Exception", this::doQueryMessageList.name)
        }

    }

    @Transactional
    override fun markMessage(list: List<String>): Result {
        try {
            val query = KtUpdateWrapper(Message::class.java)
            query.`in`(Message::id, list).set(Message::status, 1)
            baseMapper.update(null, query)
            return Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::markMessage.name + " Update Exception", e.suppressed)
            throw SqlException("Update Exception", this::markMessage.name)
        }
    }

    @Transactional
    override fun deleteMessage(list: List<String>): Result {
        try {
            val query = KtQueryWrapper(Message::class.java)
            query.`in`(Message::id, list)
            baseMapper.delete(query)
            return Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::deleteMessage.name + " Delete Exception", e.suppressed)
            throw SqlException("Delete Exception", this::deleteMessage.name)
        }
    }
}
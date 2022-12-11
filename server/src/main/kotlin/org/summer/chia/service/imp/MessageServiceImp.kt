package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.MessageMapper
import org.summer.chia.mapper.TeacherMapper
import org.summer.chia.pojo.ao.CommunicativeTarget
import org.summer.chia.pojo.ao.MessageListItem
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
    override fun doPostMessage(message: MessageObject, user: UserDetails): Result {
        val uid = (user as UserDetailsAdapter).getPayLoad().id!!
        try {
            baseMapper.insert(
                Message(
                    null,
                    Timestamp(System.currentTimeMillis()),
                    message.content,
                    uid,
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
            val res = target.filter {
                it.id!! != "System"
            }.map {
                CommunicativeTarget(it.id!!, it.name)
            }
            return Result.success(res)
        } catch (e: Exception) {
            Log.error(this.javaClass, this::doQueryCommunicative.name + " Query Exception", e.suppressed)
            throw SqlException("Query Exception", this::doQueryCommunicative.name)
        }
    }

    override fun doQueryMessageList(pageNum: String, pageSize: String, user: UserDetails): Result {
        val account = (user as UserDetailsAdapter).getPayLoad()
        val page = Page<MessageListItem>(pageNum.toLong(), pageSize.toLong())
        try {
            return when (account) {
                is Student -> {
                    val res = baseMapper.queryStudentMessage(page, account.id!!)
                    Result.success(res.records)
                }

                is Teacher -> {
                    val res = baseMapper.queryTeacherMessage(page, account.id!!)
                    Result.success(res.records)
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

    override fun doQuerySystemMessage(pageNum: String, pageSize: String, user: UserDetails): Result {
        val account = (user as UserDetailsAdapter).getPayLoad()
        val page = Page<MessageListItem>(pageNum.toLong(), pageSize.toLong())
        try {
            val res = baseMapper.querySystemMessage(page, account.id!!)
            return Result.success(res.records)
        } catch (e: Exception) {
            throw SqlException("Query Exception", this::doQuerySystemMessage.name)
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
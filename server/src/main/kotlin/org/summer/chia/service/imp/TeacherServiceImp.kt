package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.TeacherMapper
import org.summer.chia.pojo.ao.CommunicativeTarget
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.TeacherService
import org.summer.chia.utils.Log

@Service
class TeacherServiceImp : ServiceImpl<TeacherMapper, Teacher>(), TeacherService {

    override fun loadUserByUsername(username: String): UserDetails? {
        val query = KtQueryWrapper(Teacher::class.java)
        query.eq(Teacher::email, username)
        val res = baseMapper.selectOne(query)
        return if (res != null) {
            res.role = "ROLE_Teacher"
            UserDetailsAdapter(res)
        } else
            null
    }

    override fun getUserNameByAccount(user: UserDetails): Result {
        val account = ((user as UserDetailsAdapter).getPayLoad() as Teacher).email
        val query = KtQueryWrapper(Teacher::class.java)
        query.eq(Teacher::email, account)
        val res = baseMapper.selectOne(query)
        return if (res != null) {
            Result.success(res.name)
        } else {
            Log.error(this.javaClass, this::getUserNameByAccount.name + "-> Query Exception: ", null)
            throw SqlException("Query Exceptions", this::getUserNameByAccount.name)
        }
    }

    @Transactional
    override fun addTeacher(obj: Teacher, user: UserDetails): Result {
        try {
            obj.password = BCryptPasswordEncoder().encode(obj.password)
            baseMapper.insert(obj)
            return Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::addTeacher.name + "-> Insert Exception: " + e.message, e.stackTrace)
            throw SqlException("Insert Exception", this::addTeacher.name)
        }
    }

    @Transactional
    override fun deleteTeacher(tid: String): Result {
        try {
            val num = baseMapper.selectCount(KtQueryWrapper(Teacher::class.java).ne(Teacher::id, "System")).toInt()
            if (num == 0) {
                return Result.error("系统至少需要一位老师")
            }
            baseMapper.delete(KtQueryWrapper(Teacher::class.java).eq(Teacher::id, tid))
            return Result.success()
        } catch (e: Exception) {
            Log.error(this.javaClass, this::deleteTeacher.name + "-> Delete Exception: " + e.message, e.stackTrace)
            throw SqlException("Delete Exception", this::deleteTeacher.name)
        }
    }

    override fun queryTeacherList(): Result {
        try {
            val target = baseMapper.selectList(null) ?: return Result.error("暂时无老师")
            val res = target.filter {
                it.id!! != "System"
            }.map {
                CommunicativeTarget(it.id!!, it.name)
            }
            return Result.success(res)
        } catch (e: Exception) {
            Log.error(
                this.javaClass,
                this::queryTeacherList.name + "-> Query Exception: " + e.message,
                e.stackTrace
            )
            throw SqlException("Query Exception", this::queryTeacherList.name)
        }
    }

}
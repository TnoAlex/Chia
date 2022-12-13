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
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.TeacherService

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
            throw SqlException("Insert Exception", this::addTeacher.name)
        }
    }
}
package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.summer.chia.adapter.UserDetailsAdapter
import org.summer.chia.exception.RoleTypeErrorException
import org.summer.chia.exception.SqlException
import org.summer.chia.pojo.ao.RestPassword
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Student
import org.summer.chia.pojo.dto.Teacher
import org.summer.chia.service.CaptchaService
import org.summer.chia.service.StudentService
import org.summer.chia.service.TeacherService
import org.summer.chia.service.UserService
import org.summer.chia.utils.Log

@Service
class UserServiceImp : UserService {

    @Autowired
    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var teacherService: TeacherService

    @Autowired
    private lateinit var captchaService: CaptchaService
    override fun resetUserPassword(obj: RestPassword): Result {
        val bCryptPasswordEncoder = BCryptPasswordEncoder()
        val uid = (SecurityContextHolder.getContext().authentication.principal as UserDetailsAdapter).getPayLoad().id!!
        val res = captchaService.evalCode(obj.code)
        try{
            when (obj.type) {
                0 -> {
                    if (res) {
                        studentService.baseMapper.update(null,
                            KtUpdateWrapper(Student::class.java).eq(Student::id, uid)
                                .set(Student::password, bCryptPasswordEncoder.encode(obj.newPassword))
                        )
                    } else {
                        return Result.error("验证码无效，请重新获取")
                    }
                }
                1->{
                    if (res) {
                        teacherService.baseMapper.update(null,
                            KtUpdateWrapper(Teacher::class.java).eq(Teacher::id, uid)
                                .set(Teacher::password, bCryptPasswordEncoder.encode(obj.newPassword))
                        )
                    } else {
                        return Result.error("验证码无效，请重新获取")
                    }
                }
            }
        }catch (e:Exception){
            Log.error(this.javaClass, e.message!!, e.suppressed)
            throw SqlException("Update Exception",this::resetUserPassword.name)
        }
        return Result.success()
    }

    override fun loadUserByUsername(username: String): UserDetails? {
        val usernameAndRole = username.split("/")
        val name = usernameAndRole[0]
        return when (usernameAndRole[1]) {
            "1" -> {
                teacherService.loadUserByUsername(name)
            }

            "0" -> {
                studentService.loadUserByUsername(name)
            }

            else -> {
                Log.error(this.javaClass, "Incorrect Permission Descriptor", null)
                throw RoleTypeErrorException("Incorrect Permission Descriptor")
            }
        }
    }
}
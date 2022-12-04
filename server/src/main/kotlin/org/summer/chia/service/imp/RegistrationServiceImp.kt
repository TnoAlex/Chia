package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.RegistrationMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.RegistrationListItem
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.Registration
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.RegistrationService

@Service
class RegistrationServiceImp : ServiceImpl<RegistrationMapper, Registration>(), RegistrationService {

    @Autowired
    private lateinit var studentMapper: StudentMapper

    @Transactional
    override fun registrationList(objList: List<RegistrationListItem>): Result {
        val studentId = studentMapper.selectList(
            KtQueryWrapper(Student::class.java).`in`(
                Student::idNumber,
                objList.map { it.studentIdNumber })
        ).associate { it.idNumber to it.id }
        try {
            objList.forEach {
                baseMapper.insert(Registration(null, it.cspId, studentId[it.studentIdNumber]!!, null, it.type, null, 0))
            }
        } catch (e: Exception) {
            throw SqlException("Insert Exception", this::registrationList.name)
        }
        return Result.success()
    }

    @Transactional
    override fun transcriptsList(objList: List<RegistrationListItem>): Result {
        val studentId = studentMapper.selectList(
            KtQueryWrapper(Student::class.java).`in`(
                Student::idNumber,
                objList.map { it.studentIdNumber })
        ).associate { it.idNumber to it.id }
        try {
            objList.forEach {
                baseMapper.update(
                    null,
                    KtUpdateWrapper(Registration::class.java).eq(Registration::cspId, it.cspId)
                        .eq(Registration::studentId, studentId[it.studentIdNumber]!!)
                        .set(Registration::miss, 0)
                        .set(Registration::score, it.socre!!)
                        .set(Registration::state, 1)
                )
            }
        } catch (e: Exception) {
            throw SqlException("Update Exception", this::transcriptsList.name)
        }
        return Result.success()
    }

    override fun doQueryAbsentOfficialRegistration(cid: String): Result {
        TODO("Not yet implemented")
    }
}
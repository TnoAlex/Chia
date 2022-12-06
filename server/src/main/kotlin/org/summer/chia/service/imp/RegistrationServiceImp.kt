package org.summer.chia.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.summer.chia.exception.SqlException
import org.summer.chia.mapper.CspInfoMapper
import org.summer.chia.mapper.RegistrationMapper
import org.summer.chia.mapper.StudentMapper
import org.summer.chia.pojo.ao.RegistrationListItem
import org.summer.chia.pojo.ao.Result
import org.summer.chia.pojo.dto.CspInfo
import org.summer.chia.pojo.dto.Registration
import org.summer.chia.pojo.dto.Student
import org.summer.chia.service.RegistrationService

@Service
class RegistrationServiceImp : ServiceImpl<RegistrationMapper, Registration>(), RegistrationService {

    @Autowired
    private lateinit var studentMapper: StudentMapper

    @Autowired
    private lateinit var cspInfoMapper: CspInfoMapper

    @Transactional
    override fun registrationList(objList: List<RegistrationListItem>): Result {
        val studentId = studentMapper.selectList(
            KtQueryWrapper(Student::class.java).`in`(
                Student::idNumber,
                objList.map { it.studentIdNumber })
        ).associate { it.idNumber to it.id }
        try {
            objList.forEach {
                baseMapper.insert(Registration(null, it.cspId, studentId[it.studentIdNumber]!!, null, it.type, null))
            }
            val cspId = objList.map { it.cspId }.toSet()
            cspId.forEach {
                cspInfoMapper.update(
                    null,
                    KtUpdateWrapper(CspInfo::class.java).eq(CspInfo::id, it).set(CspInfo::stage, 1)
                )
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
                )
            }
            val cspId = objList.map { it.cspId }.toSet()
            cspId.forEach {
                cspInfoMapper.update(
                    null,
                    KtUpdateWrapper(CspInfo::class.java).eq(CspInfo::id, it).set(CspInfo::stage, 2)
                )
            }
        } catch (e: Exception) {
            throw SqlException("Update Exception", this::transcriptsList.name)
        }
        return Result.success()
    }

    override fun doQueryAbsentOfficialRegistration(cid: String): Result {
        val cspStage = cspInfoMapper.selectOne(KtQueryWrapper(CspInfo::class.java).eq(CspInfo::id, cid)).stage!!
        if (cspStage < 1) {
            return Result.error("正式报名名单未导入")
        }
        return try {
            val students = baseMapper.absentOfficialRegistration(cid)
            Result.success(students)
        } catch (e: Exception) {
            throw SqlException("Query Exception", this::doQueryAbsentOfficialRegistration.name)
        }
    }

    override fun doQueryAbsentExam(cid: String): Result {
        val cspStage = cspInfoMapper.selectOne(KtQueryWrapper(CspInfo::class.java).eq(CspInfo::id, cid)).stage!!
        if (cspStage < 2) {
            return Result.error("成绩单未导入")
        }
        return try {
            val students = baseMapper.absentExam(cid)
            Result.success(students)
        } catch (e: Exception) {
            throw SqlException("Query Exception", this::doQueryAbsentOfficialRegistration.name)
        }
    }
}
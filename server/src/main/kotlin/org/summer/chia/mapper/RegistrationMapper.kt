package org.summer.chia.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.ao.StudentListItem
import org.summer.chia.pojo.dto.Registration

@Mapper
interface RegistrationMapper : BaseMapper<Registration> {
    fun absentOfficialRegistration(cid: String): List<StudentListItem>
    fun absentExam(cid: String): List<StudentListItem>
}

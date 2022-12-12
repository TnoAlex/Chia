package org.summer.chia.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.ao.StudentListItem
import org.summer.chia.pojo.dto.Registration

@Mapper
interface RegistrationMapper : BaseMapper<Registration> {
    fun absentOfficialRegistration(page: Page<StudentListItem>, cid: String): Page<StudentListItem>
    fun absentOfficialRegistrationNumber(cid: String): Long
    fun absentExam(page: Page<StudentListItem>, cid: String): Page<StudentListItem>
    fun absentExamNumber(cid: String): Long
}

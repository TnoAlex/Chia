package org.summer.chia.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.ao.CSPPreRegistrationInfo
import org.summer.chia.pojo.ao.StudentListItem
import org.summer.chia.pojo.dto.PreRegistration

@Mapper
interface PreRegistrationMapper : BaseMapper<PreRegistration> {
    fun queryOne(cid: String, sid: String): CSPPreRegistrationInfo?
    fun queryList(page: Page<StudentListItem>, cid: String): Page<StudentListItem>
    fun totalNum(cid: String): Long
}

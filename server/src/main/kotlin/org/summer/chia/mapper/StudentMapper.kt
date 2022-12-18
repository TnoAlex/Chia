package org.summer.chia.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.dto.Student

@Mapper
interface StudentMapper : BaseMapper<Student> {
    fun queryRegisterList(sid: String): List<String>
}

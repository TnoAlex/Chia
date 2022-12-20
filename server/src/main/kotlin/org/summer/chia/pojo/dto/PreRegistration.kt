package org.summer.chia.pojo.dto

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.v3.oas.annotations.media.Schema
import org.summer.chia.annotation.NoArg

@NoArg
@TableName("pre_registration")
data class PreRegistration(
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(hidden = true)
    var id: String?,
    @Schema(description = "CSP ID")
    var cspId: String,
    @Schema(description = "报名学生ID")
    var studentId: String?,
    @Schema(description = "报名类型")
    var type: Int,
    @Schema(description = "报名额外提交信息")
    var extra: String,
    )

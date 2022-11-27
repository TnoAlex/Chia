package org.summer.chia.mapper
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.summer.chia.pojo.dto.Captcha

@Mapper
interface CaptchaMapper:BaseMapper<Captcha> {}

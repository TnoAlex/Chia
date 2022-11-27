package org.summer.chia.pojo.dto

import org.summer.chia.annotation.NoArg
import java.sql.Timestamp

@NoArg
data class Captcha (

  var id : String?,
  var uid : String,
  var code : String,
  var createTime : Timestamp

)

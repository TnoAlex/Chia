package org.summer.chia.utils

import java.util.*


fun verificationCode(send: Long): String {
    //生成六位随机正整数
    val random = Random(send)
    var verificationCode: String = java.lang.String.valueOf(random.nextInt(9) + 1)
    for (i in 0..4) {
        verificationCode += random.nextInt(10)
    }
    return verificationCode
}

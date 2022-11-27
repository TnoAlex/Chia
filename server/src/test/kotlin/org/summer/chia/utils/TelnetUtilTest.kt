package org.summer.chia.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TelnetUtilTest{
    @Test
    fun testTelnet(){
        TelnetUtil.testMailConnection("smtp.163.com","tommills@163.com","QCWALYFSSZANWCAY")
    }
}
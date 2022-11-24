package org.summer.chia.exception

class SqlException(message: String, val causedBy: String) : RuntimeException(message)
package org.summer.chia.exception

class TypeCastException(message: String, val causedBy: String) : RuntimeException(message)
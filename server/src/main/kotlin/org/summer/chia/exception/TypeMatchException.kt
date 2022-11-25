package org.summer.chia.exception

class TypeMatchException(message: String, val causedBy: String) : RuntimeException(message)
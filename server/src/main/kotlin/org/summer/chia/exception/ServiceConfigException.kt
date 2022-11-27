package org.summer.chia.exception

class ServiceConfigException(message: String, val causedBy: String) : RuntimeException(message)

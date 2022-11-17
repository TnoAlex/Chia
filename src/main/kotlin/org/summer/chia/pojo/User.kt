package org.summer.chia.pojo

open class User() {
    constructor(
        id: String,
        name: String,
        password: String,
        role: String?,
    ) : this() {
        id.also { this.id = it }
        password.also { this.password = it }
        name.also { this.name = it }
        this.role = role
    }

    open lateinit var id: String
    open lateinit var name: String
    open lateinit var password: String
    var role: String? = null
}
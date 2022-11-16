package org.summer.chia.pojo

open class User() {
    constructor(
        id: String,
        name: String,
        password: String,
        role: String?,
    ) : this() {
        this.id = id
        this.password = password
        this.name = name
        this.role = role
    }

    lateinit var id: String
    lateinit var name: String
    lateinit var password: String
    var role: String? = null
}
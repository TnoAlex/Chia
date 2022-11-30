package org.summer.chia.pojo.dto

open class User() {
    constructor(
        id: String?,
        name: String,
        password: String,
        email: String?,
        role: String?,
    ) : this() {
        id.also { this.id = it }
        password.also { this.password = it }
        name.also { this.name = it }
        role.also { this.role = it }
        email.also { this.email =it}
    }

    open var id: String? = null
    open lateinit var name: String
    open lateinit var password: String
    open var role: String? = null
    open var email:String?=null
}
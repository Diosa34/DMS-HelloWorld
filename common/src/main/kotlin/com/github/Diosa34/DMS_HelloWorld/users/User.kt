package com.github.Diosa34.DMS_HelloWorld.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("")
class User(
    val login: String,
    val password: String
) {
}
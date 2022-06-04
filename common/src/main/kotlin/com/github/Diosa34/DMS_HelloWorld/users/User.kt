package com.github.Diosa34.DMS_HelloWorld.users

import kotlinx.serialization.Serializable

@Serializable
class User(
    val login: String,
    val password: String
) {
}
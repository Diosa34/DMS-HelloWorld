package com.github.diosa.dms.users

import kotlinx.serialization.Serializable

@Serializable
class User(
    val login: String,
    val password: String
) {
}
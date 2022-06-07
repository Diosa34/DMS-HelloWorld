package com.github.Diosa34.DMS_HelloWorld.serialize

import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.Serializable

@Serializable
class OneLineAnswer(
    val user: User? = null,
    val result: String
)
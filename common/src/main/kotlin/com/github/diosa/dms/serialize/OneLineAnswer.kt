package com.github.diosa.dms.serialize

import com.github.diosa.dms.users.User
import kotlinx.serialization.Serializable

@Serializable
class OneLineAnswer(
    val user: User? = null,
    val result: String
)
package com.github.diosa.dms.serialize

import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.users.User
import kotlinx.serialization.Serializable

@Serializable
class Request(
    val command: BoundCommand,
    val user: User? = null
)
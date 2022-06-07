package com.github.Diosa34.DMS_HelloWorld.serialize

import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.Serializable

@Serializable
class Request(
    val command: BoundCommand,
    val user: User? = null
)
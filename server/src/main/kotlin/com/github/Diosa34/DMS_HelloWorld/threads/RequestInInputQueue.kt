package com.github.Diosa34.DMS_HelloWorld.threads

import com.github.Diosa34.DMS_HelloWorld.absctactions.BoundCommand
import com.github.Diosa34.DMS_HelloWorld.io.SocketWrap
import com.github.Diosa34.DMS_HelloWorld.users.User

class RequestInInputQueue(
    val command: BoundCommand,
    val user: User? = null,
    val socketWrap: SocketWrap
)
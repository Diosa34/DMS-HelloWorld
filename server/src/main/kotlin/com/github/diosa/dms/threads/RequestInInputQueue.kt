package com.github.diosa.dms.threads

import com.github.diosa.dms.absctactions.BoundCommand
import com.github.diosa.dms.io.SocketWrap
import com.github.diosa.dms.users.User

class RequestInInputQueue(
    val command: BoundCommand,
    val user: User? = null,
    val socketWrap: SocketWrap
)
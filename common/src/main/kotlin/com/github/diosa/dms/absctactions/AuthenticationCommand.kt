package com.github.diosa.dms.absctactions

import com.github.diosa.dms.io.BufferLogger

interface AuthenticationCommand: BoundCommand {
    fun execute(logger: BufferLogger, userCollection: CollectionOfUsers)
}
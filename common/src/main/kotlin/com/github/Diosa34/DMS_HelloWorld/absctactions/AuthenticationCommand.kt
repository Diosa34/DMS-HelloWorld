package com.github.Diosa34.DMS_HelloWorld.absctactions

import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger

interface AuthenticationCommand: BoundCommand {
    fun execute(logger: BufferLogger, userCollection: CollectionOfUsers)
}
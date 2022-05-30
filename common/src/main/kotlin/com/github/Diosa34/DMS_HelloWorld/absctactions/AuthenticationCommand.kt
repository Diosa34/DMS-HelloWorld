package com.github.Diosa34.DMS_HelloWorld.absctactions

interface AuthenticationCommand: BoundCommand {
    fun execute(logger:Logger, userCollection: CollectionOfUsers)
}
package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.*
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize

class LogIn(
    private val login: String,
    private val password: String
): AuthenticationCommand {
    override fun execute(logger: Logger, userCollection: CollectionOfUsers) {
//        logger.print(userCollection.getLogin(login, password))
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun serialize(): UByteArray {
        var bytes: UByteArray = title.serialize()
        bytes += this.login.serialize()
        bytes += this.password.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "log_in"
        override val help: String = "авторизоваться"
    }
}
package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.AuthenticationCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfUsers
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.serialize.serialize
import com.github.Diosa34.DMS_HelloWorld.users.User
import java.sql.SQLException

class Register(
    private val login: String,
    private val password: String
): AuthenticationCommand {
    override fun execute(logger: Logger, userCollection: CollectionOfUsers) {
        try {
            userCollection.add(User(this.login, this.password))
        } catch (ex: SQLException) {
            logger.print("Ошибка при регистрации")
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun serialize(): UByteArray {
        var bytes: UByteArray = title.serialize()
        bytes += this.login.serialize()
        bytes += this.password.serialize()
        return bytes
    }

    companion object Description: AbstractDescription {
        override val title: String = "register"
        override val help: String = "зарегистрироваться"
    }
}
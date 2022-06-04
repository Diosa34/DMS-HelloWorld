package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.*
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.serialize.GeneralEncoder
import com.github.Diosa34.DMS_HelloWorld.users.User
import kotlinx.serialization.Serializable
import java.sql.SQLException

@Serializable
class LogIn(
    private val login: String,
    private val password: String
): AuthenticationCommand {
    override fun execute(logger: BufferLogger, userCollection: CollectionOfUsers) {
        val userArr = ByteArray(1024*1024)
        try {
            User.serializer().serialize(GeneralEncoder(userArr.toUByteArray()), userCollection.getUser(this.login, this.password))
            logger.userToArr(userArr)
            logger.print("Пользователь $login успешно авторизован")
        } catch (ex: SQLException) {
            logger.print("Ошибка при авторизации")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "log_in"
        override val help: String = "авторизоваться"
    }
}
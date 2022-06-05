package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.*
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.serialize.GeneralEncoder
import com.github.Diosa34.DMS_HelloWorld.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.SQLException

@Serializable
@SerialName("log_in")
class LogIn(
    @DisplayName("Логин")
    private val login: String,
    @DisplayName("Пароль")
    private val password: String
): AuthenticationCommand {
    override fun execute(logger: BufferLogger, userCollection: CollectionOfUsers) {
        try {
            logger.setUser(userCollection.getUser(this.login, this.password))
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
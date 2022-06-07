package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.*
import com.github.Diosa34.DMS_HelloWorld.exceptions.NotAuthorizedException
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
            logger.print("Информация о командах доступна по команде 'help'")
        } catch (ex: NotAuthorizedException) {
            logger.print(ex.message)
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "log_in"
        override val help: String = "авторизоваться"
    }
}
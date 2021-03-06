package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.*
import com.github.diosa.dms.exceptions.NotAuthorizedException
import com.github.diosa.dms.io.BufferLogger
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Log in")
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
            logger.success = true
        } catch (ex: NotAuthorizedException) {
            logger.print(ex.message)
            logger.success = false
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "Log in"
        override val help: String = "авторизоваться"
    }
}
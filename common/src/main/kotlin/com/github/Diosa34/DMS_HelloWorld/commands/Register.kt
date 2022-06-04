package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.AuthenticationCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfUsers
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import java.sql.SQLException
import kotlinx.serialization.Serializable

@Serializable
@DisplayName("регистрация")
class Register(
    @DisplayName("логин")
    private val login: String,
    @DisplayName("пароль")
    private val password: String
): AuthenticationCommand {
    override fun execute(logger: BufferLogger, userCollection: CollectionOfUsers) {
        try {
            userCollection.add(User(this.login, this.password))
            logger.print("Пользователь успешно зарегистрирован")
        } catch (ex: SQLException) {
            logger.print("Ошибка при регистрации")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "register"
        override val help: String = "зарегистрироваться"
    }
}
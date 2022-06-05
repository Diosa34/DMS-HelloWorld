package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.AuthenticationCommand
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfUsers
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import java.sql.SQLException
import kotlinx.serialization.Serializable

@Serializable
@SerialName("registry")
class Register(
    @DisplayName("Логин")
    private val login: String,
    @DisplayName("Пароль")
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
        override val title: String = "registry"
        override val help: String = "зарегистрироваться"
    }
}
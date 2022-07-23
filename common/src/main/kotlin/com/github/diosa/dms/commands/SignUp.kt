package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.AuthenticationCommand
import com.github.diosa.dms.absctactions.CollectionOfUsers
import com.github.diosa.dms.io.BufferLogger
import com.github.diosa.dms.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import java.sql.SQLException
import kotlinx.serialization.Serializable

@Serializable
@SerialName("registry")
class SignUp(
    @DisplayName("Логин")
    private val login: String,
    @DisplayName("Пароль")
    private val password: String
): AuthenticationCommand {
    override fun execute(logger: BufferLogger, userCollection: CollectionOfUsers) {
        try {
            userCollection.add(User(this.login, this.password))
            logger.print("Пользователь успешно зарегистрирован")
            logger.success = true
        } catch (ex: SQLException) {
            logger.print("Такой пользователь уже есть в базе")
            logger.success = false
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "registry"
        override val help: String = "зарегистрироваться"
    }
}
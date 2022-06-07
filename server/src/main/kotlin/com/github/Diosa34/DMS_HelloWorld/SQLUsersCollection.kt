package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfUsers
import com.github.Diosa34.DMS_HelloWorld.exceptions.NotAuthorizedException
import com.github.Diosa34.DMS_HelloWorld.users.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class SQLUsersCollection: CollectionOfUsers {
    override fun add(user: User): User{
        transaction { Users.insert {
            it[login] = user.login
            it[password] = user.password} }
        return user
    }

    override fun getUser(login: String, password: String): User {
        return transaction {
            val users = Users.select { Users.login eq login and (Users.password eq password)}.map{ r ->
            User(r[Users.login], r[Users.password])}
            if (users.isNotEmpty()) {
            return@transaction users[0]
            } else {throw NotAuthorizedException("Неверный логин или пароль, введите команду заново")
            }}
    }
}

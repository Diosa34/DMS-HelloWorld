package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfUsers
import com.github.Diosa34.DMS_HelloWorld.users.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class SQLUsersCollection: CollectionOfUsers {
    override fun add(user: User){
        transaction { Users.insert {
            it[login] = user.login
            it[password] = user.password} }
    }

    override fun getLogin(login: String, password: String): User {
        return transaction { Users.select { Users.login eq login; Users.password eq password}.map{ r ->
            User(r[Users.login], r[Users.password])}[0] }
    }
}

package com.github.Diosa34.DMS_HelloWorld.sql

import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfUsers
import com.github.Diosa34.DMS_HelloWorld.exceptions.NotAuthorizedException
import com.github.Diosa34.DMS_HelloWorld.users.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and

class SQLUsersCollection: CollectionOfUsers {
    @Synchronized
    override fun add(user: User): User{
        transaction { Users.insert {
            it[login] = user.login
            it[salt] = UUID.randomUUID().toString().substring(0,5)
            it[password] = hash(user.password, it[salt])
        } }
        return user
    }

    @Synchronized
    override fun getUser(login: String, password: String): User {
        val salt: String = transaction { Users.select {Users.login eq login} }.map{ it[Users.salt] }[0]
        return transaction {
            val users = Users.select { Users.login eq login and (Users.password eq hash(password, salt))}.map{ r ->
            User(r[Users.login], r[Users.password])}
            if (users.isNotEmpty()) {
            return@transaction users[0]
            } else {throw NotAuthorizedException("Неверный логин или пароль, введите команду заново")
            }}
    }
}

fun hash(password: String, salt: String): String {
    var hash = ""
    val pepper = "CFG$#@*vh"
    val md = MessageDigest.getInstance("SHA-384")
    val digest = md.digest((pepper + password + salt).toByteArray())

    for (i in digest.iterator()) {
        hash += Integer.toHexString(i.and(0xff.toByte()).toInt())
    }
    return hash
}
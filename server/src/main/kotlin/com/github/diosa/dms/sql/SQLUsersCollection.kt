package com.github.diosa.dms.sql

import com.github.diosa.dms.absctactions.CollectionOfUsers
import com.github.diosa.dms.exceptions.NotAuthorizedException
import com.github.diosa.dms.users.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and

class SQLUsersCollection : CollectionOfUsers {
    @Synchronized
    override fun add(user: User) {
        transaction {
            val newSalt = UUID.randomUUID().toString().substring(0, 5)
            println(newSalt)
            Users.insert {
                it[login] = user.login
                it[salt] = newSalt
                it[password] = hash(user.password, newSalt)
            }
        }
    }

    @Synchronized
    override fun getUser(login: String, password: String): User {
        val salt = transaction {
            val currentLogin = Users.select { Users.login eq login }
            if (!currentLogin.empty()){
                return@transaction currentLogin.map { it[Users.salt] }[0]
            } else {
                throw NotAuthorizedException("Неверный логин, введите команду заново")
            }}
        return transaction {
            val users = Users.select { Users.login eq login and (Users.password eq hash(password, salt)) }.map { r ->
                User(r[Users.login], r[Users.password])
            }
            if (users.isNotEmpty()) {
                return@transaction users[0]
            } else {
                throw NotAuthorizedException("Неверный пароль, введите команду заново")
            }
        }
    }
}

fun hash(password: String, salt: String): String {
    return transaction {
        val hash = StringBuilder()
        val pepper = "#@*"
        val md = MessageDigest.getInstance("SHA-224")
        val digest = md.digest((pepper + password + salt).toByteArray())
        for (i in digest) {
            val str = "0"+Integer.toHexString(i.toInt())
            hash.append(str.substring(str.length - 2))
            println(hash.toString())
        }
        return@transaction hash.toString()
    }
}
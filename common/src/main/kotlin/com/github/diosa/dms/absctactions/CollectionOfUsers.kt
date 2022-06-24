package com.github.diosa.dms.absctactions

import com.github.diosa.dms.users.User

interface CollectionOfUsers {
    fun add(user: User)

    fun getUser(login: String, password: String): User
}
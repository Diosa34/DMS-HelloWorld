package com.github.Diosa34.DMS_HelloWorld.absctactions

import com.github.Diosa34.DMS_HelloWorld.users.User

interface CollectionOfUsers {
    fun add(user: User)

    fun getUser(login: String, password: String): User
}
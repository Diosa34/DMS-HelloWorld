package com.github.diosa.dms.io

import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.collection.CollectionInMemory
import com.github.diosa.dms.serialize.OneLineAnswer
import com.github.diosa.dms.users.User

class BufferLogger(
    val socketWrap: SocketWrap
) : Logger {
    private var user: User? = null
    private var buf = StringBuilder()
    var success: Boolean = false
    lateinit var collection: CollectionInMemory
    lateinit var answer: OneLineAnswer

    override fun print(message: String) {
        if (buf.isNotBlank()) {
            this.buf.append("\n$message")
        } else {
            this.buf.append(message)
        }
    }

    fun setUser(user: User){
        this.user = user
    }

    fun build() {
        answer = OneLineAnswer(this.user, this.buf.toString(), this.success, this.collection)
    }
}
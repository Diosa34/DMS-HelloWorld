package com.github.diosa.dms.serialize

import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.collection.CollectionInMemory
import com.github.diosa.dms.users.User
import kotlinx.serialization.Serializable

@Serializable
class OneLineAnswer(
    val user: User? = null,
    val result: String,
    val success: Boolean = false,
    val collection: CollectionInMemory? = null
)
package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AdminCommand
import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.collection.CollectionInMemory
import com.github.diosa.dms.io.BufferLogger
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("getCollection")
object GetCollection: AdminCommand, AbstractDescription {
    override val title: String = "getCollection"
    override val help: String = "предоставляет клиенту доступ к коллекции"

    override fun execute(logger: BufferLogger, collection: CollectionInMemory) {
        logger.collection = collection
    }
}
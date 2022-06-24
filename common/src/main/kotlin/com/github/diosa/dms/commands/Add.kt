package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.collection.Vehicle
import com.github.diosa.dms.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("add")
class Add(
    @DisplayName("Средство передвижения")
    private val vehicle: Vehicle
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        collection.add(vehicle, user)
        logger.print("Элемент успешно добавлен в коллекцию")
    }

    companion object Description: AbstractDescription {
        override val title: String = "add"
        override val help: String = "добавить новый элемент в коллекцию"
    }
}
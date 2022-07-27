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
@SerialName("Update")
class Update(
    @DisplayName("ID")
    private val id: Int,
    @DisplayName("Средство передвижения")
    private val vehicle: Vehicle,
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User){
        when (collection.update(id, vehicle, user)) {
            CollectionOfVehicles.UpdateResult.EMPTY -> logger.print("Коллекция пуста, элемент НЕ был обновлён")
            CollectionOfVehicles.UpdateResult.UPDATED -> logger.print("Элемент успешно обновлён")
            CollectionOfVehicles.UpdateResult.NOT_FOUND -> logger.print("Элементов с соответствующим id не найдено" +
                    "или данный элемент принадлежит другому пользователю")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "Update"
        override val help: String = "обновить значение элемента коллекции, номер которого равен заданному"
    }
}
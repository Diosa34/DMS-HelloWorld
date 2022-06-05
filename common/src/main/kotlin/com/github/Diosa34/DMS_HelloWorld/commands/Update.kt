package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.Vehicle
import com.github.Diosa34.DMS_HelloWorld.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("update")
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
            CollectionOfVehicles.UpdateResult.NOT_FOUND -> logger.print("Элементов с соответствующим id не найдено")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "update"
        override val help: String = "обновить значение элемента коллекции, номер которого равен заданному"
    }
}
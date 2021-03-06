package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Remove by id")
class RemoveById(
    @DisplayName("ID")
    private val id: Int
): ApplicableToCollection {

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        when (collection.removeById(id, user)) {
            CollectionOfVehicles.RemoveByIdResult.EMPTY -> logger.print("Коллекция пуста, элемент с id $id не удалён")
            CollectionOfVehicles.RemoveByIdResult.DELETED -> logger.print("Элемент c id $id успешно удалён")
            CollectionOfVehicles.RemoveByIdResult.NOT_FOUND -> logger.print("Элемент с id $id не найден или он " +
                    "принадлежит другому пользователю")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "Remove by id"
        override val help: String = "удалить элемент из коллекции по его номеру"
    }
}
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
@SerialName("Remove lower")
class RemoveLower(
    @DisplayName("Марка средства передвижения")
    private val name: String
): ApplicableToCollection {

    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        when (collection.removeLower(name, user)) {
            CollectionOfVehicles.RemoveLowerResult.EMPTY -> logger.print("Коллекция пуста, нет элементов для удаления")
            CollectionOfVehicles.RemoveLowerResult.DELETED -> logger.print("Элементы удалены")
            CollectionOfVehicles.RemoveLowerResult.LESS_NOT_FOUND -> logger.print("Элементов с более короткой маркой не" +
                    " найдено или они принадлежат другому пользователю")
        }
    }

    companion object Description: AbstractDescription {
        override val title: String = "Remove lower"
        override val help: String = "удалить из коллекции все элементы, меньшие, чем" +
                " заданный (элементы сравниваются по длине марки средства передвижения)"
    }
}
package com.github.diosa.dms.commands

import com.github.diosa.dms.absctactions.AbstractDescription
import com.github.diosa.dms.absctactions.ApplicableToCollection
import com.github.diosa.dms.absctactions.CollectionOfVehicles
import com.github.diosa.dms.absctactions.Logger
import com.github.diosa.dms.collection.VehicleType
import com.github.diosa.dms.users.User
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("count_by_type")
class CountByType(
    @DisplayName("Тип средства передвижения")
    private val type: VehicleType
): ApplicableToCollection {
    override fun execute(logger: Logger, collection: CollectionOfVehicles, user: User) {
        logger.print("Количество средств передвижения типа ${this.type}:" +
                " ${collection.countByType(type)}")
    }

    companion object Description: AbstractDescription {
        override val title: String = "count_by_type"
        override val help: String = "вывести количество элементов, значение типа которых равно заданному"
    }
}
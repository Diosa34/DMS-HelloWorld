package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.absctactions.AbstractDescription
import com.github.Diosa34.DMS_HelloWorld.absctactions.ApplicableToCollection
import com.github.Diosa34.DMS_HelloWorld.absctactions.CollectionOfVehicles
import com.github.Diosa34.DMS_HelloWorld.absctactions.Logger
import com.github.Diosa34.DMS_HelloWorld.collection.VehicleType
import com.github.Diosa34.DMS_HelloWorld.users.User
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
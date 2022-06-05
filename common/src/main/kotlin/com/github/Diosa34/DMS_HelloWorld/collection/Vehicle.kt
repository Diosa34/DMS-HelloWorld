package com.github.Diosa34.DMS_HelloWorld.collection

import com.github.Diosa34.DMS_HelloWorld.serialize.InstantEpochSecondsSerializer
import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.datetime.Instant
import kotlinx.datetime.Clock.System
import kotlinx.serialization.Serializable

/**
 * Description of the entity, objects in the com.github.Diosa34.DMS_HelloWorld.collection
 */
@Serializable
class Vehicle(
    @DisplayName("ID")
    var id: Int? = null, //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @DisplayName("Название")
    val name: String, //Поле не может быть null, Строка не может быть пустой
    @DisplayName("Координаты")
    val coordinates: Coordinates, //Поле не может быть null
    @Serializable(InstantEpochSecondsSerializer::class)
    @DisplayName("Дата создания")
    val creationDate: Instant = System.now(), //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @DisplayName("Мощность двигателя")
    val enginePower: Float, //Поле не может быть null, Значение поля должно быть больше 0
    @DisplayName("Тип средства передвижения")
    val type: VehicleType, //Поле не может быть null
    @DisplayName("Тип топлива")
    val fuelType: FuelType //Поле может быть null
){
    override fun toString(): String {
        return """
            Номер: ${this.id}
            Марка: ${this.name}
            Координаты: ${this.coordinates}
            Дата создания: ${this.creationDate}
            Мощность двигателя: ${this.enginePower}
            Тип средства передвижения: ${this.type}
            Тип топлива: ${this.fuelType}
            """.trimIndent()
    }

    operator fun compareTo(o: String): Int {
        return name.length - o.length
    }
}
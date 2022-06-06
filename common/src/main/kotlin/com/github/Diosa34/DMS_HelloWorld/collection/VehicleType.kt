@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.collection

import io.github.landgrafhomyak.itmo.dms_lab.interop.DisplayName
import kotlinx.serialization.Serializable

private var countOfInstances = 0

@Serializable
enum class VehicleType {
    @DisplayName("CAR")
    CAR,
    @DisplayName("SUBMARINE")
    SUBMARINE,
    @DisplayName("SHIP")
    SHIP;

    var index: Int = countOfInstances++

    companion object{
        @JvmStatic
        private val types: Map<Int, VehicleType> = mapOf(
                CAR.index to CAR,
                SUBMARINE.index to SUBMARINE,
                SHIP.index to SHIP
        )

        @JvmStatic
        fun getVehicle(index: Int): VehicleType? {
            return types[index]
        }

        @JvmStatic
        fun getTypes(): Map<Int, VehicleType> {
            return types
        }

        @JvmStatic
        val stringToType: Map<String, VehicleType> = mapOf(
            "CAR" to CAR,
            "SUBMARINE" to SUBMARINE,
            "SHIP" to SHIP
        )

        @JvmStatic
        val typeToString: Map<VehicleType, String> = mapOf(
            CAR to "CAR",
            SUBMARINE to "SUBMARINE",
            SHIP to "SHIP"
        )
    }
}
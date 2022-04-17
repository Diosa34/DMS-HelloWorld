package com.github.Diosa34.DMS_HelloWorld.enums

private var countOfInstances = 0

enum class FuelType {
    GASOLINE,
    DIESEL,
    NUCLEAR;

    var index: Int = countOfInstances++

    companion object{
        @JvmStatic
        private val types: Map<Int, FuelType> = mapOf(
            GASOLINE.index to GASOLINE,
            DIESEL.index to DIESEL,
            NUCLEAR.index to NUCLEAR
        )

        @JvmStatic
        fun getFuel(index: Int): FuelType? {
            return types[index]
        }

        @JvmStatic
        fun getTypes(): Map<Int, FuelType> {
            return types
        }

        @JvmStatic
        val stringToType: Map<String, FuelType> = mapOf(
            "GASOLINE" to GASOLINE,
            "DIESEL" to DIESEL,
            "NUCLEAR" to NUCLEAR
        )
    }
}
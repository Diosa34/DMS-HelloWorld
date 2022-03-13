package classes

private var countOfInstances = 0

enum class VehicleType {
    CAR,
    SUBMARINE,
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

//        @JvmStatic
//        fun typesToString() {
//            types.keys.forEach{key ->
//                println("$key - ${types[key]}")
//            }
//        }
    }
}
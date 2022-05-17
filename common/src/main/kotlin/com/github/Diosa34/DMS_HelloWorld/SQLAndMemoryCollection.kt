package com.github.Diosa34.DMS_HelloWorld

class SQLAndMemoryCollection: CollectionOfVehicles {
    private val sql = SQLCollection()

    override fun add(vehicle: Vehicle) {
        sql.add(vehicle)
        CollectionInMemory.add(vehicle)
    }

    override fun addIfMin(name: String, vehicle: Vehicle): CollectionOfVehicles.AddIfMinResult {
        val sqlResult = sql.addIfMin(name, vehicle)
        val sqlSuccess = sqlResult.isSuccess
        val memoryResult = CollectionInMemory.addIfMin(name, vehicle)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка добавления минимального элемента:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }

    override fun clear() {
        sql.clear()
        CollectionInMemory.clear()
    }

    override fun countByType(type: VehicleType): Int {
        return CollectionInMemory.countByType(type)
    }

    override fun groupCountingByType(): Groups {
        return CollectionInMemory.groupCountingByType()
    }

    override fun info(): CollectionOfVehicles.Information {
        return CollectionInMemory.info()
    }

    override fun removeById(id: Int): CollectionOfVehicles.RemoveByIdResult {
        val sqlResult = sql.removeById(id)
        val sqlSuccess = sqlResult.isSuccess
        val memoryResult = CollectionInMemory.removeById(id)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка удаления элемента с id $id:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }

    override fun removeFirst(): Boolean {
        val result = sql.removeFirst()
        if (result == CollectionInMemory.removeFirst()) {
            return result
        } else {
            throw CollectionException("Ошибка удаления первого элемента")
        }
    }

    override fun removeLower(name: String): CollectionOfVehicles.RemoveLowerResult {
        val sqlResult = sql.removeLower(name)
        val sqlSuccess = sqlResult.isSuccess
        val memoryResult = CollectionInMemory.removeLower(name)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка удаления меньших элементов:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }


    override fun iterator(): Iterator<Vehicle> {
        return CollectionInMemory.iterator()
    }

    override fun sumOfEnginePower(): Float {
        return CollectionInMemory.sumOfEnginePower()
    }

    override fun update(id: Int, vehicle: Vehicle): CollectionOfVehicles.UpdateResult {
        val sqlResult = sql.update(id, vehicle)
        val sqlSuccess = sqlResult.isSuccess
        val memoryResult = CollectionInMemory.update(id, vehicle)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка обновления элемента с id $id:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }
}
package com.github.Diosa34.DMS_HelloWorld

class SQLAndMemoryCollection: CollectionOfVehicles {
    private val collectionInMemory = CollectionInMemory()
    private val sqlCollection = SQLCollection()

    fun getCollectionInMemory(): CollectionInMemory {
        return this.collectionInMemory
    }

    fun getSqlCollection(): SQLCollection {
        return this.sqlCollection
    }

    override fun print() {
        collectionInMemory.print()
    }

    override fun add(vehicle: Vehicle) {
        this.sqlCollection.add(vehicle)
        vehicle.id = this.sqlCollection.selectMaxId()
        this.collectionInMemory.add(vehicle)
    }

    override fun addIfMin(name: String, vehicle: Vehicle): CollectionOfVehicles.AddIfMinResult {
        val sqlResult = this.sqlCollection.addIfMin(name, vehicle)
        val sqlSuccess = sqlResult.isSuccess
        vehicle.id = this.sqlCollection.selectMaxId()
        val memoryResult = this.collectionInMemory.addIfMin(name, vehicle)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка добавления минимального элемента:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }

    override fun clear() {
        this.sqlCollection.clear()
        this.collectionInMemory.clear()
    }

    override fun countByType(type: VehicleType): Int {
        return this.collectionInMemory.countByType(type)
    }

    override fun groupCountingByType(): Groups {
        return this.collectionInMemory.groupCountingByType()
    }

    override fun info(): CollectionOfVehicles.Information {
        return this.collectionInMemory.info()
    }

    override fun removeById(id: Int): CollectionOfVehicles.RemoveByIdResult {
        val sqlResult = this.sqlCollection.removeById(id)
        val sqlSuccess = sqlResult.isSuccess
        val memoryResult = this.collectionInMemory.removeById(id)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка удаления элемента с id $id:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }

    override fun removeFirst(): Boolean {
        val result = this.sqlCollection.removeFirst()
        if (result == this.collectionInMemory.removeFirst()) {
            return result
        } else {
            throw CollectionException("Ошибка удаления первого элемента")
        }
    }

    override fun removeLower(name: String): CollectionOfVehicles.RemoveLowerResult {
        val sqlResult = this.sqlCollection.removeLower(name)
        val sqlSuccess = sqlResult.isSuccess
        val memoryResult = this.collectionInMemory.removeLower(name)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка удаления меньших элементов:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }

    override fun iterator(): Iterator<Vehicle> {
        return this.collectionInMemory.iterator()
    }

    override fun sumOfEnginePower(): Float {
        return this.collectionInMemory.sumOfEnginePower()
    }

    override fun update(id: Int, vehicle: Vehicle): CollectionOfVehicles.UpdateResult {
        val sqlResult = this.sqlCollection.update(id, vehicle)
        val sqlSuccess = sqlResult.isSuccess
        vehicle.id = id
        val memoryResult = this.collectionInMemory.update(id, vehicle)
        val memorySuccess = memoryResult.isSuccess
        if (sqlSuccess == memorySuccess) {
            return memoryResult
        } else {
            throw CollectionException("Ошибка обновления элемента с id $id:\nsql-результат: $sqlResult\n" +
                    "результат работы с коллекцией в памяти: $memoryResult")
        }
    }
}
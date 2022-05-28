package com.github.Diosa34.DMS_HelloWorld.absctactions

interface ApplicableToCollection: BoundCommand {
    fun execute(logger: Logger, collection: CollectionOfVehicles)
}
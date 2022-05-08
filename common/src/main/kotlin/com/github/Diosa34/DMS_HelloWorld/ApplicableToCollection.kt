package com.github.Diosa34.DMS_HelloWorld

interface ApplicableToCollection: BoundCommand {
    fun execute(logger: Logger, collection: CollectionOfVehicles)
}
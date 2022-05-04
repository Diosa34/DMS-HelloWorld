package com.github.Diosa34.DMS_HelloWorld.commands

import com.github.Diosa34.DMS_HelloWorld.collection.CollectionOfVehicles

//
interface ApplicableToCollection: BoundCommand {
    fun execute(collection: CollectionOfVehicles): Unit
}
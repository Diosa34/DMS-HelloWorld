package com.github.Diosa34.DMS_HelloWorld.absctactions

import com.github.Diosa34.DMS_HelloWorld.users.User

interface ApplicableToCollection: BoundCommand {
    fun execute(logger: Logger, collection: CollectionOfVehicles, user: User)
}
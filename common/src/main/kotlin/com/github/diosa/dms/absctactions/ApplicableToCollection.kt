package com.github.diosa.dms.absctactions

import com.github.diosa.dms.users.User

interface ApplicableToCollection: BoundCommand {
    fun execute(logger: Logger, collection: CollectionOfVehicles, user: User)
}
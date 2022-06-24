package com.github.diosa.dms

import com.github.diosa.dms.absctactions.*
import com.github.diosa.dms.commands.*
import com.github.diosa.dms.io.BufferLogger
import com.github.diosa.dms.sql.SQLUsersCollection
import com.github.diosa.dms.users.User

fun executeCall(command: BoundCommand, logger: BufferLogger, collection: CollectionOfVehicles,
                usersCollection: SQLUsersCollection, user: User?){
    when (command) {
        is ApplicableToCollection -> if (user != null) {
            command.execute(logger, collection, user)
        }
        is SystemCommand -> command.execute(logger)
        is Help -> command.execute(logger, Add.Description, AddIfMin.Description, Clear,
            CountByType.Description, ExecuteScript.Description, Exit, GroupCountingByType, Help,
            Info, RemoveById.Description, RemoveFirst, RemoveLower.Description, Show, SumOfEnginePower,
            Update.Description)
        is AuthenticationCommand -> command.execute(logger, usersCollection)
    }
}
package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.*
import com.github.Diosa34.DMS_HelloWorld.commands.*
import com.github.Diosa34.DMS_HelloWorld.io.BufferLogger
import com.github.Diosa34.DMS_HelloWorld.sql.SQLUsersCollection
import com.github.Diosa34.DMS_HelloWorld.users.User

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
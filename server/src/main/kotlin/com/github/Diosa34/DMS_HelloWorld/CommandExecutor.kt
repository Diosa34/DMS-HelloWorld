package com.github.Diosa34.DMS_HelloWorld

import com.github.Diosa34.DMS_HelloWorld.absctactions.*
import com.github.Diosa34.DMS_HelloWorld.commands.*

fun executeCall(command: BoundCommand, logger: Logger, collection: CollectionOfVehicles){
    when (command) {
        is ApplicableToCollection ->
            command.execute(logger, collection)
        is SystemCommand -> command.execute(logger)
        is Help -> command.execute(logger, Add.Description, AddIfMin.Description, Clear,
            CountByType.Description, ExecuteScript.Description, Exit, GroupCountingByType, Help,
            Info, RemoveById.Description, RemoveFirst, RemoveLower.Description, Show, SumOfEnginePower,
            Update.Description)
    }
}
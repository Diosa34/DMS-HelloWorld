package com.github.Diosa34.DMS_HelloWorld

fun executeCall(command: BoundCommand, logger: Logger){
    when (command) {
        is ApplicableToCollection -> command.execute(logger, CollectionOfVehicles.globalCollection)
        is SystemCommand -> command.execute(logger)
        is Help -> command.execute(logger, Add.Description, AddIfMin.Description, Clear,
            CountByType.Description, ExecuteScript.Description, Exit, GroupCountingByType, Help,
            Info, RemoveById.Description, RemoveFirst, RemoveLower.Description, Show, SumOfEnginePower,
            Update.Description)
    }
}
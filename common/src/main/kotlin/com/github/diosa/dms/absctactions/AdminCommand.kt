package com.github.diosa.dms.absctactions

import com.github.diosa.dms.collection.CollectionInMemory
import com.github.diosa.dms.io.BufferLogger

interface AdminCommand: BoundCommand{
    fun execute(logger: BufferLogger, collection: CollectionInMemory)
}
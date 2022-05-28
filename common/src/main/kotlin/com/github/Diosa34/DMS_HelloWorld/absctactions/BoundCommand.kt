@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.absctactions

import java.io.Serializable

/**
 * Entity describing the command
 */
interface BoundCommand: Serializable {
    fun serialize(): UByteArray
}


@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld.absctactions

import com.github.Diosa34.DMS_HelloWorld.serialize.InterfaceSerializer
import kotlinx.serialization.Serializable

/**
 * Entity describing the command
 */
@Serializable(InterfaceSerializer::class)
interface BoundCommand


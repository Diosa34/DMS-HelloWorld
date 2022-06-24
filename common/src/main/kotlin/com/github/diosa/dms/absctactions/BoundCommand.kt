package com.github.diosa.dms.absctactions

import com.github.diosa.dms.serialize.InterfaceSerializer
import kotlinx.serialization.Serializable

/**
 * Entity describing the command
 */
@Serializable(InterfaceSerializer::class)
interface BoundCommand


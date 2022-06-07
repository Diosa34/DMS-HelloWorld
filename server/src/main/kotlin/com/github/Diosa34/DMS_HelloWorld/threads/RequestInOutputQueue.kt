package com.github.Diosa34.DMS_HelloWorld.threads

import com.github.Diosa34.DMS_HelloWorld.io.SocketWrap
import com.github.Diosa34.DMS_HelloWorld.serialize.OneLineAnswer

class RequestInOutputQueue(
    val answer: OneLineAnswer,
    val socketWrap: SocketWrap
)
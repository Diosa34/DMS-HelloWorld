package com.github.diosa.dms.threads

import com.github.diosa.dms.io.SocketWrap
import com.github.diosa.dms.serialize.OneLineAnswer

class RequestInOutputQueue(
    val answer: OneLineAnswer,
    val socketWrap: SocketWrap
)
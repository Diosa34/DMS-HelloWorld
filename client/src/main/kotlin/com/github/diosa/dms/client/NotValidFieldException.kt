package com.github.diosa.dms.client

import java.io.IOException

class NotValidFieldException(override val message: String): IOException() {
}
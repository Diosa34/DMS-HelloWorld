package com.github.diosa.dms.exceptions

import java.io.IOException

class NotValidFieldException(override val message: String): IOException() {
}
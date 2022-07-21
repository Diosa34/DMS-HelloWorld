package com.github.diosa.dms.exceptions

import java.io.IOException

class MismatchedPasswordsException(override val message: String): IOException()
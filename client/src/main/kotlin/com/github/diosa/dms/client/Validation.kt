package com.github.diosa.dms.client

/**
 * Verification the validity of an argument
 */
fun <T: Any> tryGet(field: String, number: String.() -> T?) : T? {
    field.number().let { number ->
        if (number != null) {
            return@tryGet number
        }
        return null
    }
}


package com.github.Diosa34.DMS_HelloWorld

import org.junit.Test
import kotlin.test.assertContentEquals

internal class ShowTest {
    @Test
    fun testShow() {
        @Suppress("RemoveExplicitTypeArguments")
        val expected = listOf<Int>(1234, 8765, Int.MAX_VALUE, Int.MIN_VALUE, -12344512, -1234, -143, 0)
        val actual = expected.map { f -> f.serialize().iterator().deserializeInt() }
        assertContentEquals(expected, actual)
    }
}
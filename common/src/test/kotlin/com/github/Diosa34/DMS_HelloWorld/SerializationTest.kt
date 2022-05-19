@file:OptIn(ExperimentalUnsignedTypes::class)

package com.github.Diosa34.DMS_HelloWorld

import kotlin.test.Test
import kotlin.test.assertContentEquals

internal class SerializationTest {
    @Test
    fun testInt() {
        @Suppress("RemoveExplicitTypeArguments")
        val expected = listOf<Int>(1234, 8765, Int.MAX_VALUE, Int.MIN_VALUE, -12344512, -1234, -143, 0)
        val actual = expected.map { f -> f.serialize().iterator().deserializeInt() }
        assertContentEquals(expected, actual)
    }

    @Test
    fun testFloat() {
        @Suppress("RemoveExplicitTypeArguments")
        val expected = listOf<Float>(123.5f, 0.1f, 45.9f, 6.0f, -4567.466f, -56.78f)
        val actual = expected.map { f -> f.serialize().iterator().deserializeFloat() }
        assertContentEquals(expected, actual)
    }
}
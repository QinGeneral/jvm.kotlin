package com.elements.jvmbykotlin.runtimedata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LocalVariableTest {
    @Test
    fun testIntGetSet() {
        val intValue = 1
        val index = 1
        val localVariable = LocalVariable(2)

        localVariable.setInt(index, intValue)

        assertEquals(intValue, localVariable.getInt(index))
    }

    @Test
    fun testFloatGetSet() {
        val floatValue = 3.14f
        val index = 1
        val localVariable = LocalVariable(2)

        localVariable.setFloat(index, floatValue)

        assertEquals(floatValue, localVariable.getFloat(index))
    }

    @Test
    fun testLongGetSet() {
        val longValue = 1024000000000L
        val index = 1
        val localVariable = LocalVariable(3)

        localVariable.setLong(index, longValue)

        assertEquals(longValue, localVariable.getLong(index))
    }

    @Test
    fun testDoubleGetSet() {
        val doubleValue = 3.14
        val index = 1
        val localVariable = LocalVariable(3)

        localVariable.setDouble(index, doubleValue)

        assertEquals(doubleValue, localVariable.getDouble(index))
    }

    @Test
    fun testRefGetSet() {
        val refValue = YuObject()
        val index = 1
        val localVariable = LocalVariable(3)

        localVariable.setRef(index, refValue)

        assertEquals(refValue, localVariable.getRef(index))
    }
}
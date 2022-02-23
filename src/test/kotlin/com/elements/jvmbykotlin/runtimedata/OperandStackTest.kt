package com.elements.jvmbykotlin.runtimedata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class OperandStackTest {
    @Test
    fun testIntPushPop() {
        val operandStack = OperandStack(1)
        val value = 1
        operandStack.pushInt(value)

        assertEquals(value, operandStack.popInt())
    }

    @Test
    fun testFloatPushPop() {
        val operandStack = OperandStack(1)
        val value = 3.14f
        operandStack.pushFloat(value)

        assertEquals(value, operandStack.popFloat())
    }

    @Test
    fun testLongPushPop() {
        val operandStack = OperandStack(2)
        val value = 1024000000000L
        operandStack.pushLong(value)

        assertEquals(value, operandStack.popLong())
    }

    @Test
    fun testDoublePushPop() {
        val operandStack = OperandStack(2)
        val value = 3.14
        operandStack.pushDouble(value)

        assertEquals(value, operandStack.popDouble())
    }

    @Test
    fun testRefPushPop() {
        val operandStack = OperandStack(1)
        val value = YuObject()
        operandStack.pushRef(value)

        assertEquals(value, operandStack.popRef())
    }
}
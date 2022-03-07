package com.elements.jvmbykotlin.runtimedata

import com.elements.jvmbykotlin.TestUtils
import com.elements.jvmbykotlin.runtimedata.heap.YuObject
import org.junit.jupiter.api.Assertions.assertEquals
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
        val value = 2.71828
        operandStack.pushDouble(value)

        assertEquals(value, operandStack.popDouble())
    }

    @Test
    fun testRefPushPop() {
        val operandStack = OperandStack(1)
        val value = YuObject(TestUtils.mockClass(), LocalVariable())
        operandStack.pushRef(value)

        assertEquals(value, operandStack.popRef())
    }
}
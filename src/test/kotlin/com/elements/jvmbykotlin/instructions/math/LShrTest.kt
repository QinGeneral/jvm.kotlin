package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LShrTest {
    @Test
    fun testLShl() {
        val frame = Frame(YuThread(), 0, 2)
        val value = 5L
        val shift = 2
        frame.operandStack.pushLong(value)
        frame.operandStack.pushInt(shift)

        LShr().execute(frame)

        assertEquals(value shr shift, frame.operandStack.popLong())
    }
}
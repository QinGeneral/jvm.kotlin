package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.TestUtils
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LShlTest {
    @Test
    fun testLShl() {
        val frame = Frame(YuThread(), TestUtils.mockMethod(0, 2))
        val value = 5L
        val shift = 2
        frame.operandStack.pushLong(value)
        frame.operandStack.pushInt(shift)

        LShl().execute(frame)

        assertEquals(value shl shift, frame.operandStack.popLong())
    }
}
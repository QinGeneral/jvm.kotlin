package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.TestUtils
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class IShlTest {
    @Test
    fun testIShl() {
        val frame = Frame(YuThread(), TestUtils.mockMethod(0, 2))
        val value = 3
        val shift = 2
        frame.operandStack.pushInt(value)
        frame.operandStack.pushInt(shift)

        IShl().execute(frame)

        assertEquals(value shl shift, frame.operandStack.popInt())
    }
}
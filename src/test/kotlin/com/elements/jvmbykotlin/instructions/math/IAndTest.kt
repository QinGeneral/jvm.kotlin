package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.TestUtils
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class IAndTest {
    @Test
    fun testIAnd() {
        val frame = Frame(YuThread(), TestUtils.mockMethod(0, 2))
        val v1 = 5
        val v2 = 2
        frame.operandStack.pushInt(v1)
        frame.operandStack.pushInt(v2)

        IAnd().execute(frame)

        assertEquals(v1 and v2, frame.operandStack.popInt())
    }
}
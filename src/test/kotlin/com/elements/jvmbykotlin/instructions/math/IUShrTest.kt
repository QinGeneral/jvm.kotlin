package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class IUShrTest {
    @Test
    fun testIUShr() {
        val frame = Frame(YuThread(), 0, 2)
        val value = 5
        val shift = 2
        frame.operandStack.pushInt(value)
        frame.operandStack.pushInt(shift)

        IShr().execute(frame)

        assertEquals(value ushr shift, frame.operandStack.popInt())
    }
}
package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class IRem : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        if (v2 == 0) {
            throw ArithmeticException("/ by zero")
        }
        val result = v1 % v2
        stack.pushInt(result)
    }
}
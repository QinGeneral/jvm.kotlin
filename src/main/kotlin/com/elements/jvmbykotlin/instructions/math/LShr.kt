package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LShr : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popLong()
        val s = v2 and 0x3f
        val result = v1 shr s
        stack.pushLong(result)
    }
}
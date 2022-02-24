package com.elements.jvmbykotlin.instructions.conversions

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class L2D : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val l = stack.popLong()
        stack.pushDouble(l.toDouble())
    }
}
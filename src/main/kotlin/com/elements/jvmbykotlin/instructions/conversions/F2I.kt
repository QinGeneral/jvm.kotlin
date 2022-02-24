package com.elements.jvmbykotlin.instructions.conversions

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class F2I : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val f = stack.popFloat()
        stack.pushInt(f.toInt())
    }
}
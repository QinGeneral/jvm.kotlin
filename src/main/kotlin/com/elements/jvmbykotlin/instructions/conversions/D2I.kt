package com.elements.jvmbykotlin.instructions.conversions

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class D2I : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val d = stack.popDouble()
        stack.pushInt(d.toInt())
    }
}
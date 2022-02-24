package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class DRem : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popDouble()
        val v1 = stack.popDouble()
        val result = v1.mod(v2)
        stack.pushDouble(result)
    }
}
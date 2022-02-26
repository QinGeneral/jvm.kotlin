package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class DConst0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushDouble(0.0)
    }
}

class DConst1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushDouble(1.0)
    }
}
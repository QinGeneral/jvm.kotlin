package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LConst0Instruction : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushLong(0)
    }
}
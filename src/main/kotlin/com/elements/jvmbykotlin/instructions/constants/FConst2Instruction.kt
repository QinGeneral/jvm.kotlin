package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class FConst2Instruction : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(2.0f)
    }
}
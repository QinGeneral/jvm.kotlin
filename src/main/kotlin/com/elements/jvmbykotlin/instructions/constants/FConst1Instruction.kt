package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class FConst1Instruction : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(1.0f)
    }
}
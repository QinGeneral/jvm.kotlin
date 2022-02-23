package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class IConst3Instruction : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(3)
    }
}
package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class Pop2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.popSlot()
        frame.operandStack.popSlot()
    }
}
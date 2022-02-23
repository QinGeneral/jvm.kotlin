package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class Pop : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.popSlot()
    }
}
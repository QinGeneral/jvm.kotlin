package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class DupX1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot1 = frame.operandStack.popSlot()
        val slot2 = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot1)
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
    }
}
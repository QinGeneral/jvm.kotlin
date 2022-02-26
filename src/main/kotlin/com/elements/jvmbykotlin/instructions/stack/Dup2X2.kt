package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class Dup2X2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot1 = frame.operandStack.popSlot()
        val slot2 = frame.operandStack.popSlot()
        val slot3 = frame.operandStack.popSlot()
        val slot4 = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
        frame.operandStack.pushSlot(slot4)
        frame.operandStack.pushSlot(slot3)
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
    }
}
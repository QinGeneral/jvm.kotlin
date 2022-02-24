package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class Dup2X2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        //todo

        val slot = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot)
        frame.operandStack.pushSlot(slot)
    }
}
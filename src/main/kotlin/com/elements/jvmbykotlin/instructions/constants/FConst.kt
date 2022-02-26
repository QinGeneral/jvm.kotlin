package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class FConst0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(0.0f)
    }
}

class FConst1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(1.0f)
    }
}

class FConst2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(2.0f)
    }
}
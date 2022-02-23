package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class AConstNullInstruction: NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushRef(null)
    }
}
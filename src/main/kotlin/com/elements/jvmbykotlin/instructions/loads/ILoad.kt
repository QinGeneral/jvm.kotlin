package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class ILoad : Index8Instruction() {
    companion object {
        fun iLoad(frame: Frame, index: Int) {
            val value = frame.localVariable.getInt(index)
            frame.operandStack.pushInt(value)
        }
    }

    override fun execute(frame: Frame) {
        iLoad(frame, index)
    }
}
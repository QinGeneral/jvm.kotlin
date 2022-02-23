package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LStore : Index8Instruction() {
    companion object {
        fun lStore(frame: Frame, index: Int) {
            val value = frame.operandStack.popLong()
            frame.localVariable.setLong(index, value)
        }
    }

    override fun execute(frame: Frame) {
        lStore(frame, index)
    }
}
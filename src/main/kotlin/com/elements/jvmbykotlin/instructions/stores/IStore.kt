package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class IStore : Index8Instruction() {
    companion object {
        fun iStore(frame: Frame, index: Int) {
            val value = frame.operandStack.popInt()
            frame.localVariable.setInt(index, value)
        }
    }

    override fun execute(frame: Frame) {
        iStore(frame, index)
    }
}
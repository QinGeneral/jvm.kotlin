package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LLoad : Index8Instruction() {
    companion object {
        fun lLoad(frame: Frame, index: Int) {
            val value = frame.localVariable.getLong(index)
            frame.operandStack.pushLong(value)
        }
    }

    override fun execute(frame: Frame) {
        lLoad(frame, index)
    }
}

class LLoad0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 0)
    }
}

class LLoad1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 1)
    }
}

class LLoad2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 2)
    }
}

class LLoad3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 3)
    }
}
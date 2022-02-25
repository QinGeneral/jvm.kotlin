package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class FLoad : Index8Instruction() {
    companion object {
        fun fLoad(frame: Frame, index: Int) {
            val value = frame.localVariable.getFloat(index)
            frame.operandStack.pushFloat(value)
        }
    }

    override fun execute(frame: Frame) {
        fLoad(frame, index)
    }
}

class FLoad0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FLoad.fLoad(frame, 0)
    }
}

class FLoad1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FLoad.fLoad(frame, 1)
    }
}

class FLoad2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FLoad.fLoad(frame, 2)
    }
}

class FLoad3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FLoad.fLoad(frame, 3)
    }
}
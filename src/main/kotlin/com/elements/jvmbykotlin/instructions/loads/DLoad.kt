package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class DLoad : Index8Instruction() {
    companion object {
        fun dLoad(frame: Frame, index: Int) {
            val value = frame.localVariable.getDouble(index)
            frame.operandStack.pushDouble(value)
        }
    }

    override fun execute(frame: Frame) {
        dLoad(frame, index)
    }
}

class DLoad0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 0)
    }
}

class DLoad1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 1)
    }
}

class DLoad2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 2)
    }
}

class DLoad3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 3)
    }
}
package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class ALoad : Index8Instruction() {
    companion object {
        fun aLoad(frame: Frame, index: Int) {
            val value = frame.localVariable.getRef(index)
            frame.operandStack.pushRef(value)
        }
    }

    override fun execute(frame: Frame) {
        aLoad(frame, index)
    }
}

class ALoad0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 0)
    }
}

class ALoad1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 1)
    }
}

class ALoad2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 2)
    }
}

class ALoad3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DLoad.dLoad(frame, 3)
    }
}
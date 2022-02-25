package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class DStore : Index8Instruction() {
    companion object {
        fun dStore(frame: Frame, index: Int) {
            val value = frame.operandStack.popDouble()
            frame.localVariable.setDouble(index, value)
        }
    }

    override fun execute(frame: Frame) {
        dStore(frame, index)
    }
}

class DStore0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DStore.dStore(frame, 0)
    }
}

class DStore1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DStore.dStore(frame, 1)
    }
}

class DStore2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DStore.dStore(frame, 2)
    }
}

class DStore3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DStore.dStore(frame, 3)
    }
}
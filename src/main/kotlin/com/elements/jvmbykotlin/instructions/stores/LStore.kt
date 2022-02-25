package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
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

class LStore0 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        LStore.lStore(frame, 0)
    }
}

class LStore1 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        LStore.lStore(frame, 1)
    }
}

class LStore2 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        LStore.lStore(frame, 2)
    }
}

class LStore3 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        LStore.lStore(frame, 3)
    }
}
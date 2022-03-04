package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Instruction store integer
 *
 * Store integer from operand stack to local variables
 */
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

class IStore0 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        IStore.iStore(frame, 0)
    }
}

class IStore1 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        IStore.iStore(frame, 1)
    }
}

class IStore2 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        IStore.iStore(frame, 2)
    }
}

class IStore3 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        IStore.iStore(frame, 3)
    }
}
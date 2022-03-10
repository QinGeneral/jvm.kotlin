package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Store int in operand stack to local variable
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.istore">istore</a>
 *
 * @author hanzhang
 * @since 2022-02-24
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
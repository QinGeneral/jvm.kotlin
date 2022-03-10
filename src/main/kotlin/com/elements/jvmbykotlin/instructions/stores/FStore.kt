package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Store float in operand stack to local variable
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.fstore">fstore</a>
 *
 * @author hanzhang
 * @since 2022-02-24
 */
class FStore : Index8Instruction() {
    companion object {
        fun fStore(frame: Frame, index: Int) {
            val value = frame.operandStack.popFloat()
            frame.localVariable.setFloat(index, value)
        }
    }

    override fun execute(frame: Frame) {
        fStore(frame, index)
    }
}

class FStore0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FStore.fStore(frame, 0)
    }
}

class FStore1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FStore.fStore(frame, 1)
    }
}

class FStore2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FStore.fStore(frame, 2)
    }
}

class FStore3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FStore.fStore(frame, 3)
    }
}
package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Store long in operand stack to local variable
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.lstore">lstore</a>
 *
 * @author hanzhang
 * @since 2022-02-24
 */
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
package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Store object in operand stack to local variable
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.astore">astore</a>
 *
 * @author hanzhang
 * @since 2022-02-24
 */
class AStore : Index8Instruction() {
    companion object {
        fun aStore(frame: Frame, index: Int) {
            val value = frame.operandStack.popRef()
            frame.localVariable.setRef(index, value)
        }
    }

    override fun execute(frame: Frame) {
        aStore(frame, index)
    }
}

class AStore0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        AStore.aStore(frame, 0)
    }
}

class AStore1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        AStore.aStore(frame, 1)
    }
}

class AStore2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        AStore.aStore(frame, 2)
    }
}

class AStore3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        AStore.aStore(frame, 3)
    }
}
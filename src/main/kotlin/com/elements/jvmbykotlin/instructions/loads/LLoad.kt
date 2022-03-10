package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Load long in local variable to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.lload">lload</a>
 *
 * @author hanzhang
 */
class LLoad : Index8Instruction() {
    companion object {
        fun lLoad(frame: Frame, index: Int) {
            val value = frame.localVariable.getLong(index)
            frame.operandStack.pushLong(value)
        }
    }

    override fun execute(frame: Frame) {
        lLoad(frame, index)
    }
}

class LLoad0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 0)
    }
}

class LLoad1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 1)
    }
}

class LLoad2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 2)
    }
}

class LLoad3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        LLoad.lLoad(frame, 3)
    }
}
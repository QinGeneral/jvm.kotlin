package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Load int in local variable to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.iload">iload</a>
 *
 * @author hanzhang
 */
class ILoad : Index8Instruction() {
    companion object {
        fun iLoad(frame: Frame, index: Int) {
            val value = frame.localVariable.getInt(index)
            frame.operandStack.pushInt(value)
        }
    }

    override fun execute(frame: Frame) {
        iLoad(frame, index)
    }
}

class ILoad0 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        ILoad.iLoad(frame, 0)
    }
}

class ILoad1 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        ILoad.iLoad(frame, 1)
    }
}

class ILoad2 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        ILoad.iLoad(frame, 2)
    }
}

class ILoad3 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        ILoad.iLoad(frame, 3)
    }
}
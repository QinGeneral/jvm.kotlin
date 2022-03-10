package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Push double 0.0/1.0 to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.dconst_d">dconst_d</a>
 *
 * @author hanzhang
 */
class DConst0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushDouble(0.0)
    }
}

class DConst1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushDouble(1.0)
    }
}
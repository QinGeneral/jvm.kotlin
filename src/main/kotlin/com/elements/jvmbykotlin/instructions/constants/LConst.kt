package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Push long 0/1 to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.fconst_f">fconst_f</a>
 *
 * @author hanzhang
 */
class LConst0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushLong(0)
    }
}

class LConst1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushLong(1)
    }
}
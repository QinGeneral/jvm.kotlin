package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Push null to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.aconst_null">aconst_null</a>
 *
 * @author hanzhang
 */
class AConstNull : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushRef(null)
    }
}
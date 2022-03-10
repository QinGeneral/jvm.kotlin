package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Push float 0.0/1.0/2.0 to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.fconst_f">fconst_f</a>
 *
 * @author hanzhang
 */
class FConst0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(0.0f)
    }
}

class FConst1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(1.0f)
    }
}

class FConst2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushFloat(2.0f)
    }
}
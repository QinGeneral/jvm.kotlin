package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Pop the slot on top of operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.pop">pop</a>
 *
 * @author hanzhang
 */
class Pop : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.popSlot()
    }
}

class Pop2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.popSlot()
        frame.operandStack.popSlot()
    }
}
package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Compare long
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.lcmp">lcmp</a>
 *
 * @author hanzhang
 */
class LCmp : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popLong()
        val v1 = stack.popLong()
        if (v1 > v2) {
            stack.pushInt(1)
        } else if (v1 == v2) {
            stack.pushInt(0)
        } else {
            stack.pushInt(-1)
        }
    }
}
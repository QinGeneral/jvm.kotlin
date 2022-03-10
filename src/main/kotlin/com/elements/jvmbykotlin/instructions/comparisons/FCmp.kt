package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Compare float
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.fcmp_op">fcmp_op</a>
 *
 * @author hanzhang
 */
class FCmp : NoOperationsInstruction() {
    companion object {
        fun fcmp(frame: Frame, gFlag: Boolean) {
            val stack = frame.operandStack
            val v2 = stack.popFloat()
            val v1 = stack.popFloat()
            if (v1 > v2) {
                stack.pushInt(1)
            } else if (v1 == v2) {
                stack.pushInt(0)
            } else if (v1 < v2) {
                stack.pushInt(-1)
            } else if (gFlag) {
                stack.pushInt(1)
            } else {
                stack.pushInt(-1)
            }
        }
    }
}

class FCmpG : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FCmp.fcmp(frame, true)
    }
}

class FCmpL : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FCmp.fcmp(frame, false)
    }
}
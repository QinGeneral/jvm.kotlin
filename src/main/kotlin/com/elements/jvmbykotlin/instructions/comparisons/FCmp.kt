package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

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
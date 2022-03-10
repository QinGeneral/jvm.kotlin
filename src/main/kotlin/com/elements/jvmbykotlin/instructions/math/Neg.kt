package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Get negative value
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.ineg">ineg</a>
 *
 * @author hanzhang
 */
class INEG : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v1 = stack.popInt()
        stack.pushInt(-v1)
    }
}

class LNEG : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v1 = stack.popLong()
        stack.pushLong(-v1)
    }
}

class FNEG : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v1 = stack.popFloat()
        stack.pushFloat(-v1)
    }
}

class DNEG : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v1 = stack.popDouble()
        stack.pushDouble(-v1)
    }
}
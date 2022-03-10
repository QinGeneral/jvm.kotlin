package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * div two value
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.idiv">idiv</a>
 *
 * @author hanzhang
 */
class IDIV : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        val result = v1 / v2
        stack.pushInt(result)
    }
}

class LDIV : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popLong()
        val v1 = stack.popLong()
        val result = v1 / v2
        stack.pushLong(result)
    }
}

class FDIV : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popFloat()
        val v1 = stack.popFloat()
        val result = v1 / v2
        stack.pushFloat(result)
    }
}

class DDIV : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popDouble()
        val v1 = stack.popDouble()
        val result = v1 / v2
        stack.pushDouble(result)
    }
}
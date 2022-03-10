package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * remainder two value
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.irem">irem</a>
 *
 * @author hanzhang
 */
class IRem : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        if (v2 == 0) {
            throw ArithmeticException("/ by zero")
        }
        val result = v1 % v2
        stack.pushInt(result)
    }
}

class LRem : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popLong()
        val v1 = stack.popLong()
        val result = v1.mod(v2)
        stack.pushLong(result)
    }
}

class FRem : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popFloat()
        val v1 = stack.popFloat()
        val result = v1.mod(v2)
        stack.pushFloat(result)
    }
}

class DRem : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popDouble()
        val v1 = stack.popDouble()
        val result = v1.mod(v2)
        stack.pushDouble(result)
    }
}
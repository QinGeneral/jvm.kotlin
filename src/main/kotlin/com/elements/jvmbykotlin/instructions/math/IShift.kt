package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * shift left/right/unsigned right int
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.ishl">ishl</a>
 *
 * @author hanzhang
 */
class IShl : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        val s = v2 and 0x1f
        val result = v1 shl s
        stack.pushInt(result)
    }
}

class IShr : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        val s = v2 and 0x1f
        val result = v1 shr s
        stack.pushInt(result)
    }
}

class IUShr : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        val s = v2 and 0x1f
        val result = v1 ushr s
        stack.pushInt(result)
    }
}
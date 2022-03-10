package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * shift left/right/unsigned right long
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.lshl">lshl</a>
 *
 * @author hanzhang
 */
class LShl : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popLong()
        val s = v2 and 0x3f
        val result = v1 shl s
        stack.pushLong(result)
    }
}

class LShr : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popLong()
        val s = v2 and 0x3f
        val result = v1 shr s
        stack.pushLong(result)
    }
}

class LUShr : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popLong()
        val s = v2 and 0x3f
        val result = v1 ushr s
        stack.pushLong(result)
    }
}
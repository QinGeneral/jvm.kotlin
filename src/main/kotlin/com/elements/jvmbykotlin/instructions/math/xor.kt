package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class IXOR : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        val result = v1 xor v2
        stack.pushInt(result)
    }
}

class LXOR : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popLong()
        val v1 = stack.popLong()
        val result = v1 xor v2
        stack.pushLong(result)
    }
}
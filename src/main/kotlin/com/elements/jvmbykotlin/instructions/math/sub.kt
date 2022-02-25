package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame


class ISUB : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popInt()
        val v1 = stack.popInt()
        val result = v1 - v2
        stack.pushInt(result)
    }
}

class LSUB : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popLong()
        val v1 = stack.popLong()
        val result = v1 - v2
        stack.pushLong(result)
    }
}

class FSUB : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popFloat()
        val v1 = stack.popFloat()
        val result = v1 - v2
        stack.pushFloat(result)
    }
}

class DSUB : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val v2 = stack.popDouble()
        val v1 = stack.popDouble()
        val result = v1 - v2
        stack.pushDouble(result)
    }
}
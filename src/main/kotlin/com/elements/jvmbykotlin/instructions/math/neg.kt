package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

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
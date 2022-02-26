package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LDC2_W : Index8Instruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val constantPool = frame.method.yuClass.constantPool
        val c = constantPool.getConstant(index)
        when (c) {
            is Long ->
                stack.pushLong(c)
            is Double ->
                stack.pushDouble(c)
            else ->
                throw UnsupportedOperationException("Unsupported $c")
        }
    }
}
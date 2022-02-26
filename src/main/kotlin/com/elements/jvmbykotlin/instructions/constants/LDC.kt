package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LDC : Index8Instruction() {
    companion object {
        fun ldc(frame: Frame, index: Int) {
            val stack = frame.operandStack
            val constantPool = frame.method.yuClass.constantPool
            val c = constantPool.getConstant(index)
            when (c) {
                is Int ->
                    stack.pushInt(c)
                is Float ->
                    stack.pushFloat(c)
//                is String ->
                // todo
//                    stack.pushInt(c)
//                is ClassRef ->
                // todo
//                    stack.pushInt(c)
                else ->
                    throw UnsupportedOperationException("Unsupported $c")
            }
        }
    }

    override fun execute(frame: Frame) {
        ldc(frame, index)
    }
}
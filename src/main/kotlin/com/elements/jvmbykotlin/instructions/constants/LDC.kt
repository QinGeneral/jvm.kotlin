package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.classfile.entity.constantpool.FloatInfo
import com.elements.jvmbykotlin.classfile.entity.constantpool.IntegerInfo
import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.YuString

class LDC : Index8Instruction() {
    companion object {
        fun ldc(frame: Frame, index: Int) {
            val stack = frame.operandStack
            val cla = frame.method.yuClass
            val constantPool = frame.method.yuClass.constantPool
            val c = constantPool.getConstant(index)
            println("LDC $c")
            when (c) {
                is Int ->
                    stack.pushInt(c)
                is IntegerInfo ->
                    stack.pushInt(c.iValue)
                is Float ->
                    stack.pushFloat(c)
                is FloatInfo ->
                    stack.pushFloat(c.fValue)
                is String -> {
                    val str = InternedString.jString(cla.loader, c)
                    stack.pushRef(str)
                }
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
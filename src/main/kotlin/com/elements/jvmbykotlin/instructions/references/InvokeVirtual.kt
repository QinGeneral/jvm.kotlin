package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.MethodRef

class InvokeVirtual : Index16Instruction() {
    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val methodRef = constantPool.getConstant(index) as MethodRef
        if (methodRef.name == "println") {
            val stack = frame.operandStack
            when (methodRef.descriptor) {
                "(Z)V" ->
                    println("InvokeVirtual ${stack.popInt() != 0}")
                "(C)V" ->
                    println("InvokeVirtual ${stack.popInt()}")
                "(B)V" ->
                    println("InvokeVirtual ${stack.popInt()}")
                "(S)V" ->
                    println("InvokeVirtual ${stack.popInt()}")
                "(I)V" ->
                    println("InvokeVirtual ${stack.popInt()}")
                "(J)V" ->
                    println("InvokeVirtual ${stack.popLong()}")
                "(F)V" ->
                    println("InvokeVirtual ${stack.popFloat()}")
                "(D)V" ->
                    println("InvokeVirtual ${stack.popDouble()}")
                else ->
                    println("not support method ${methodRef.descriptor}")
            }
            stack.popRef()
        }
    }
}
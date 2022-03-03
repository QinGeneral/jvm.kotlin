package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject

class ArrayLength : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val arrayRef = stack.popRef()
        if (arrayRef == null) {
            throw NullPointerException()
        }
        val arrayLength = (arrayRef as ArrayObject).arrayLength()
        stack.pushInt(arrayLength)
    }
}
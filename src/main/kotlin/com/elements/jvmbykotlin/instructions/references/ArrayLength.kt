package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject

/**
 * Get array length
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.arraylength">arraylength</a>
 *
 * @author hanzhang
 */
class ArrayLength : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val arrayRef = stack.popRef() ?: throw NullPointerException()
        val arrayLength = (arrayRef as ArrayObject).arrayLength()
        stack.pushInt(arrayLength)
    }
}
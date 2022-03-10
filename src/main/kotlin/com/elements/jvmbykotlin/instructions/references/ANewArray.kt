package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.ClassRef

/**
 * Create object type array object
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.anewarray">anewarray</a>
 *
 * @author hanzhang
 */
class ANewArray : Index16Instruction() {
    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val classRef = constantPool.getConstant(index) as ClassRef
        val componentClass = classRef.resolvedClass()

        val stack = frame.operandStack
        val count = stack.popInt()
        if (count < 0) {
            throw NegativeArraySizeException("Array index is lower then zero $count")
        }
        val arrayClass = componentClass.getArrayClass()
        val array = ArrayObject.of(arrayClass, count)
        stack.pushRef(array)
    }
}
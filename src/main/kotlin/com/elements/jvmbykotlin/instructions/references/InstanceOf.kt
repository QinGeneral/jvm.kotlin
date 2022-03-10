package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ClassRef

/**
 * If current instance is object of class
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.instanceof">instanceof</a>
 *
 * @author hanzhang
 */
class InstanceOf : Index16Instruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val ref = stack.popRef()
        if (ref == null) {
            stack.pushInt(0)
            return
        }

        val constantPool = frame.method.yuClass.constantPool
        val classRef = constantPool.getConstant(index) as ClassRef
        val c = classRef.resolvedClass()
        if (ref.isInstanceOf(c)) {
            stack.pushInt(1)
        } else {
            stack.pushInt(0)
        }
    }
}
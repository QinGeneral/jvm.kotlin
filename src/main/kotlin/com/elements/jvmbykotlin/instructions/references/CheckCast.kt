package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ClassRef

class CheckCast : Index16Instruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val ref = stack.popRef()
        stack.pushRef(ref)
        if (ref == null) {
            return
        }

        val constantPool = frame.method.yuClass.constantPool
        val classRef = constantPool.getConstant(index) as ClassRef
        val c = classRef.resolvedClass()
        if (!ref.isInstanceOf(c)) {
            throw ClassCastException("Object $ref can't cast to ${c.name}")
        }
    }
}
package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ClassRef

class New : Index16Instruction() {
    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val classRef = constantPool.getConstant(index) as ClassRef
        val c = classRef.resolvedClass()
        if (c.isInterface() or c.isAbstract()) {
            throw InstantiationError("new ${c.name} failed")
        }
        val ref = c.createObject(c)
        frame.operandStack.pushRef(ref)
    }
}
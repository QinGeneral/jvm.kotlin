package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.ClassInitLogic
import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ClassRef

/**
 * Create object
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.new">new</a>
 *
 * @author hanzhang
 */
class New : Index16Instruction() {
    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val classRef = constantPool.getConstant(index) as ClassRef
        val c = classRef.resolvedClass()
        if (c.isInterface() or c.isAbstract()) {
            throw InstantiationError("new ${c.name} failed")
        }
        if (!c.isInitStarted) {
            frame.revertNextPC()
            ClassInitLogic.initClass(frame.thread, c)
            return
        }
        val ref = c.createObject(c)
        frame.operandStack.pushRef(ref)
    }
}
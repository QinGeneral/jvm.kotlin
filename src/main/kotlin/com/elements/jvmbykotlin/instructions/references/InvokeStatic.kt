package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.ClassIntLogic
import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.instructions.base.InvokeLogic
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ref.MethodRef

class InvokeStatic : Index16Instruction() {
    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val methodRef = constantPool.getConstant(index) as MethodRef
        val resolvedMethod = methodRef.resolveMethod()
        if (!resolvedMethod.isStatic()) {
            throw IncompatibleClassChangeError("Method is not static")
        }
        val c = resolvedMethod.yuClass
        if (!c.isInitStarted) {
            frame.revertNextPC()
            ClassIntLogic.initClass(frame.thread, c)
            return
        }
        InvokeLogic.invokeMethod(frame, resolvedMethod)
    }
}
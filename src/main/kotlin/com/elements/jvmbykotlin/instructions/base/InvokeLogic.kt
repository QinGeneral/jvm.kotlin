package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

object InvokeLogic {
    fun invokeMethod(invokeFrame: Frame, method: YuMethod) {
        val thread = invokeFrame.thread
        val nextFrame = Frame(thread, method)
        thread.pushFrame(nextFrame)
        val argSlotCount = method.argSlotCount
        if (argSlotCount > 0) {
            for (i in (argSlotCount - 1) downTo 0) {
                val slot = invokeFrame.operandStack.popSlot()
                nextFrame.localVariable.setSlot(i, slot)
            }
        }
        // todo hack for native method
        if (method.isNative()) {
            if (method.name == "registerNatives") {
                thread.popFrame()
            } else {
                throw NoSuchMethodError("Method no support ${method.yuClass.name} ${method.name} ${method.descriptor}")
            }
        }
    }
}
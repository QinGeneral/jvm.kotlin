package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.instructions.base.InvokeLogic
import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.InternedString

class JavaVM {
    init {
        Registry.register(
            "sun/misc/VM", "initialize",
            "()V"
        ) { frame: Frame -> initialize(frame) }
    }

    private fun initialize(frame: Frame) {
        val vmClass = frame.method.yuClass
        val savedProps = vmClass.getRefVariable("savedProps", "Ljava/util/Properties;")
        val key = InternedString.jString(vmClass.loader, "foo")
        val value = InternedString.jString(vmClass.loader, "bar")
        frame.operandStack.pushRef(savedProps)
        frame.operandStack.pushRef(key)
        frame.operandStack.pushRef(value)
        val propsClass = vmClass.loader.loadClass("java/util/Properties")
        val setPropMethod =
            propsClass.getInstanceMethod("setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;")

        InvokeLogic.invokeMethod(frame, setPropMethod!!)
    }
}
package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.instructions.base.InvokeLogic
import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame

class JavaVM {
    init {
        Registry.register(
            "sun/misc/VM", "initialize",
            "()V"
        ) { frame: Frame -> initialize(frame) }
    }

    private fun initialize(frame: Frame) {
        val classLoader = frame.method.yuClass.loader
        val jlSysClass = classLoader.loadClass("java/lang/System")
        val initSysClass = jlSysClass.getStaticMethod("initializeSystemClass", "()V")
        InvokeLogic.invokeMethod(frame, initSysClass!!)
    }
}
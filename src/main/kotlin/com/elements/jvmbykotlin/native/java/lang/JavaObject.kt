package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame

class JavaObject {
    fun init() {
        Registry.register(
            "java/lang/Object", "getClass", "()Ljava/lang/Class;"
        ) { frame: Frame -> getClass(frame) }
    }

    // public final native Class<?> getClass();
    // ()Ljava/lang/Class;
    fun getClass(frame: Frame) {
        val thiz = frame.localVariable.getThis()
        val c = thiz.yuClass.jClass
        frame.operandStack.pushRef(c)
    }
}
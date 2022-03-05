package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.YuClass

class JavaClass {
    fun init() {
        Registry.register(
            "java/lang/Class",
            "getPrimitiveClass",
            "(Ljava/lang/String;)Ljava/lang/Class;"
        ) { frame: Frame -> getPrimitiveClass(frame) }
        Registry.register(
            "java/lang/Class",
            "getName0",
            "()Ljava/lang/String;"
        ) { frame: Frame -> getName0(frame) }
        Registry.register(
            "java/lang/Class",
            "desiredAssertionStatus0",
            "(Ljava/lang/Class;)Z"
        ) { frame: Frame -> desiredAssertionStatus0(frame) }
    }

    // static native Class<?> getPrimitiveClass(String name);
    private fun getPrimitiveClass(frame: Frame) {
        val nameObject = frame.localVariable.getThis()
        val name = InternedString.string(nameObject)
        val loader = frame.method.yuClass.loader
        val c = loader.loadClass(name).jClass
        frame.operandStack.pushRef(c)
    }

    // private native String getName0();
    private fun getName0(frame: Frame) {
        val thiz = frame.localVariable.getThis()
        val c = thiz.extra as YuClass
        val name = c.javaName
        val nameObject = InternedString.jString(c.loader, name)
        frame.operandStack.pushRef(nameObject)
    }

    // private static native boolean desiredAssertionStatus0(Class<?> clazz);
    private fun desiredAssertionStatus0(frame: Frame) {
        // todo
        frame.operandStack.pushBoolean(false)
    }
}
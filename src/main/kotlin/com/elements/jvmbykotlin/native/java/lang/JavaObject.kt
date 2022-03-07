package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame

class JavaObject {
    init {
        Registry.register(
            "java/lang/Object", "getClass", "()Ljava/lang/Class;"
        ) { frame: Frame -> getClass(frame) }
        Registry.register(
            "java/lang/Object", "hashCode", "()I"
        ) { frame: Frame -> hashCode(frame) }
        Registry.register(
            "java/lang/Object", "clone", "()Ljava/lang/Object;"
        ) { frame: Frame -> clone(frame) }
    }

    // public final native Class<?> getClass();
    // ()Ljava/lang/Class;
    private fun getClass(frame: Frame) {
        val thiz = frame.localVariable.getThis()
        val c = thiz.yuClass.jClass
        frame.operandStack.pushRef(c)
    }

    private fun hashCode(frame: Frame) {
        val thiz = frame.localVariable.getThis()
        val hash = thiz.hashCode()
        frame.operandStack.pushInt(hash)
    }

    private fun clone(frame: Frame) {
        val thiz = frame.localVariable.getThis()
        val cloneable = thiz.yuClass.loader.loadClass("java/lang/Cloneable")
        if (!thiz.yuClass.isImplements(cloneable)) {
            throw CloneNotSupportedException()
        }
        frame.operandStack.pushRef(thiz.clone())
    }
}
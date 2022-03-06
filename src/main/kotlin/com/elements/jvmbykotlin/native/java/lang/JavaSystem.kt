package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.YuObject

class JavaSystem {
    init {
        Registry.register(
            "java/lang/System", "arraycopy",
            "(Ljava/lang/Object;ILjava/lang/Object;II)V"
        ) { frame: Frame -> arraycopy(frame) }
    }

    private fun arraycopy(frame: Frame) {
        val localVariable = frame.localVariable
        val src = localVariable.getRef(0)
        val srcPos = localVariable.getInt(1)
        val dest = localVariable.getRef(2)
        val destPos = localVariable.getInt(3)
        val length = localVariable.getInt(4)
        if (src == null || dest == null) {
            throw NullPointerException()
        }
        if (!checkArrayCopy(src, dest)) {
            throw ArrayStoreException()
        }
        System.arraycopy((src as ArrayObject).data, srcPos, (dest as ArrayObject).data, destPos, length)
    }

    private fun checkArrayCopy(src: YuObject, dest: YuObject): Boolean {
        val srcClass = src.yuClass
        val destClass = dest.yuClass
        if (!srcClass.isArray() || !destClass.isArray()) {
            return false
        }
        if (srcClass.getComponentClass().isPrimitive() || destClass.getComponentClass().isPrimitive()) {
            return srcClass == destClass
        }
        return true
    }
}

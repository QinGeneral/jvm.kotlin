package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.runtimedata.LocalVariable

class YuObject(val yuClass: YuClass, val fields: LocalVariable) {
    fun isInstanceOf(yuClass: YuClass): Boolean {
        return yuClass.isAssignableFrom(yuClass)
    }

    override fun toString(): String {
        return "YuObject(yuClass=${yuClass.name}, fields=${fields.maxLocals})"
    }
}
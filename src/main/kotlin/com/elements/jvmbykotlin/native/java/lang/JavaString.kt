package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.InternedString

class JavaString {
    init {
        Registry.register(
            "java/lang/String", "intern", "()Ljava/lang/String;",
        ) { frame: Frame -> intern(frame) }
    }

    private fun intern(frame: Frame) {
        val thiz = frame.localVariable.getThis()
        val interned = InternedString.internString(thiz)
        frame.operandStack.pushRef(interned)
    }
}
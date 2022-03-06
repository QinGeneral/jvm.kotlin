package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame

class JavaFloat {
    init {
        Registry.register(
            "java/lang/Float", "floatToRawIntBits", "(F)I"
        ) { frame: Frame -> floatToRawIntBits(frame) }
    }

    private fun floatToRawIntBits(frame: Frame) {
        val value = frame.localVariable.getFloat(0)
        val bits = value.toBits()
        frame.operandStack.pushInt(bits)
    }
}
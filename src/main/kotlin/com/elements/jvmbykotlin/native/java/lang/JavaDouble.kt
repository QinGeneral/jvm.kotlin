package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame

class JavaDouble {
    init {
        Registry.register(
            "java/lang/Double", "doubleToRawLongBits", "(D)J"
        ) { frame: Frame -> doubleToRawLongBits(frame) }
        Registry.register(
            "java/lang/Double", "longBitsToDouble", "(J)D"
        ) { frame: Frame -> longBitsToDouble(frame) }
    }

    private fun doubleToRawLongBits(frame: Frame) {
        val value = frame.localVariable.getDouble(0)
        val bits = value.toRawBits()
        frame.operandStack.pushLong(bits)
    }

    private fun longBitsToDouble(frame: Frame) {
        val value = frame.localVariable.getLong(0)
        val bits = Double.fromBits(value)
        frame.operandStack.pushDouble(bits)
    }
}
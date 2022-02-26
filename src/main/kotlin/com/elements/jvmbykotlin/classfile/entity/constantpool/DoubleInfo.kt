package com.elements.jvmbykotlin.classfile.entity.constantpool

import com.elements.jvmbykotlin.classfile.Utils

class DoubleInfo(
    tag: UShort, highBytes: ByteArray, lowBytes: ByteArray
) : LongInfo(
    tag, highBytes, lowBytes
) {
    val dValue: Double
        get() {
            return Utils.byteToDouble(highBytes, lowBytes)
        }

    override fun toString(): String {
        return "DoubleInfo(value=$lValue) ${super.toString()}"
    }
}
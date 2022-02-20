package com.elements.jvmbygo.classfile.entity.constantpool

import com.elements.jvmbygo.classfile.Utils

class DoubleInfo(
    tag: UShort, highBytes: ByteArray, lowBytes: ByteArray
) : LongInfo(
    tag, highBytes, lowBytes
) {
    val value: Double
        get() {
            return Utils.byteToDouble(highBytes, lowBytes)
        }

    override fun toString(): String {
        return "DoubleInfo(value=$value) ${super.toString()}"
    }
}
package com.elements.jvmbykotlin.classfile.entity.constantpool

import com.elements.jvmbykotlin.classfile.Utils

open class LongInfo(
    tag: UShort, highBytes: ByteArray, lowBytes: ByteArray
) : BaseConstantPoolItem(tag) {
    val highBytes: ByteArray
    val lowBytes: ByteArray
    val lValue: Long
        get() {
            return Utils.byteToLong(highBytes, lowBytes)
        }

    init {
        this.highBytes = highBytes
        this.lowBytes = lowBytes
    }

    override fun toString(): String {
        return "LongInfo(highBytes=${highBytes.contentToString()}, lowBytes=${lowBytes.contentToString()}, value=$lValue)"
    }
}
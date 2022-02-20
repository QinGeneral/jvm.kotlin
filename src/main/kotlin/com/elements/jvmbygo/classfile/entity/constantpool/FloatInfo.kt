package com.elements.jvmbygo.classfile.entity.constantpool

import com.elements.jvmbygo.classfile.Utils

open class FloatInfo(tag: UShort, bytes: ByteArray) : BaseConstantPoolItem(tag) {
    val bytes: ByteArray
    private val value: Float
        get() {
            return Utils.byteToFloat(bytes)
        }

    init {
        this.bytes = bytes
    }

    override fun toString(): String {
        return "FloatInfo(bytes=${bytes.contentToString()}, value=$value)"
    }
}
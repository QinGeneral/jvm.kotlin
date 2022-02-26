package com.elements.jvmbykotlin.classfile.entity.constantpool

import com.elements.jvmbykotlin.classfile.Utils

open class FloatInfo(tag: UShort, bytes: ByteArray) : BaseConstantPoolItem(tag) {
    val bytes: ByteArray
    val fValue: Float
        get() {
            return Utils.byteToFloat(bytes)
        }

    init {
        this.bytes = bytes
    }

    override fun toString(): String {
        return "FloatInfo(bytes=${bytes.contentToString()}, value=$fValue)"
    }
}
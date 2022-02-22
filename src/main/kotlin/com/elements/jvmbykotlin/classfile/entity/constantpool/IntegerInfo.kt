package com.elements.jvmbykotlin.classfile.entity.constantpool

import com.elements.jvmbykotlin.classfile.Utils

class IntegerInfo(tag: UShort, bytes: ByteArray) : FloatInfo(tag, bytes) {
    val value: Int
        get() {
            return Utils.byteArrayToInt(bytes)
        }

    override fun toString(): String {
        return "IntegerInfo(value=$value) ${super.toString()}"
    }
}
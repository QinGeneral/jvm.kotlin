package com.elements.jvmbykotlin.classfile.entity.constantpool

class Utf8Info(tag: UShort, length: UShort, bytes: ByteArray) : BaseConstantPoolItem(tag) {
    val length: UShort
    val bytes: ByteArray

    val value: String
        get() {
            return String(bytes)
        }

    init {
        this.length = length
        this.bytes = bytes
    }

    override fun toString(): String {
        return "Utf8Info(length=$length, bytes=${bytes.contentToString()}, value='$value')"
    }
}
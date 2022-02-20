package com.elements.jvmbygo.classfile.entity.constantpool

class StringInfo(tag: UShort, stringIndex: UShort) : BaseConstantPoolItem(tag) {
    val stringIndex: UShort

    init {
        this.stringIndex = stringIndex
    }

    override fun toString(): String {
        return "StringInfo(stringIndex=$stringIndex) ${super.toString()}"
    }
}
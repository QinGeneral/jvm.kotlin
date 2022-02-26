package com.elements.jvmbykotlin.classfile.entity.constantpool

class StringInfo(tag: UShort, stringIndex: UShort) : BaseConstantPoolItem(tag) {
    val stringIndex: UShort

    fun getValue(constantPool: ConstantPool): String {
        return (constantPool.getItem(stringIndex.toInt()) as Utf8Info).value
    }

    init {
        this.stringIndex = stringIndex
    }

    override fun toString(): String {
        return "StringInfo(stringIndex=$stringIndex) ${super.toString()}"
    }
}
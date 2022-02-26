package com.elements.jvmbykotlin.classfile.entity.constantpool

class ClassInfo(tag: UShort, nameIndex: UShort) : BaseConstantPoolItem(tag) {
    val nameIndex: UShort

    fun getValue(constantPool: ConstantPool): String {
        return (constantPool.getItem(nameIndex.toInt()) as Utf8Info).value
    }

    init {
        this.nameIndex = nameIndex
    }

    override fun toString(): String {
        return "ClassInfo(nameIndex=$nameIndex) ${super.toString()}"
    }
}
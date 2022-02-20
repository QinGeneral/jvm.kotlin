package com.elements.jvmbygo.classfile.entity.constantpool

class ClassInfo(tag: UShort, nameIndex: UShort) : BaseConstantPoolItem(tag) {
    val nameIndex: UShort

    init {
        this.nameIndex = nameIndex
    }

    override fun toString(): String {
        return "ClassInfo(nameIndex=$nameIndex) ${super.toString()}"
    }
}
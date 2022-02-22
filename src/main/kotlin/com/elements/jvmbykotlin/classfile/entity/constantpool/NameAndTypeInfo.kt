package com.elements.jvmbykotlin.classfile.entity.constantpool

class NameAndTypeInfo(tag: UShort, nameIndex: UShort, descriptorIndex: UShort) : BaseConstantPoolItem(tag) {
    val nameIndex: UShort
    val descriptorIndex: UShort

    init {
        this.nameIndex = nameIndex
        this.descriptorIndex = descriptorIndex
    }

    override fun toString(): String {
        return "NameAndTypeInfo(nameIndex=$nameIndex, descriptorIndex=$descriptorIndex) ${super.toString()}"
    }
}
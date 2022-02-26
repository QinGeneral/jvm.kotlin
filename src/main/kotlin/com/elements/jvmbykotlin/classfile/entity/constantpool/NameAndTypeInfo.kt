package com.elements.jvmbykotlin.classfile.entity.constantpool

class NameAndTypeInfo(tag: UShort, nameIndex: UShort, descriptorIndex: UShort) : BaseConstantPoolItem(tag) {
    val nameIndex: UShort
    val descriptorIndex: UShort

    fun getName(constantPool: ConstantPool): String {
        return (constantPool.getItem(nameIndex.toInt()) as Utf8Info).value
    }

    fun getDescriptor(constantPool: ConstantPool): String {
        return (constantPool.getItem(descriptorIndex.toInt()) as Utf8Info).value
    }

    init {
        this.nameIndex = nameIndex
        this.descriptorIndex = descriptorIndex
    }

    override fun toString(): String {
        return "NameAndTypeInfo(nameIndex=$nameIndex, descriptorIndex=$descriptorIndex) ${super.toString()}"
    }
}
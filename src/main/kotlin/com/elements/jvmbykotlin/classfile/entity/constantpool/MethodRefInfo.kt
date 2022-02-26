package com.elements.jvmbykotlin.classfile.entity.constantpool

open class MethodRefInfo(tag: UShort, classIndex: UShort, nameAndTypeIndex: UShort) : BaseConstantPoolItem(tag) {
    val classIndex: UShort
    val nameAndTypeIndex: UShort

    fun getClassName(constantPool: ConstantPool): String {
        return constantPool.getClassName(classIndex.toInt())
    }

    fun getNameAndType(constantPool: ConstantPool): NameAndTypeInfo {
        return constantPool.getItem(nameAndTypeIndex.toInt()) as NameAndTypeInfo
    }

    init {
        this.classIndex = classIndex
        this.nameAndTypeIndex = nameAndTypeIndex
    }

    override fun toString(): String {
        return "MethodRefInfo(classIndex=$classIndex, nameAndTypeIndex=$nameAndTypeIndex) ${super.toString()}"
    }
}
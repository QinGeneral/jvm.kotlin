package com.elements.jvmbykotlin.classfile.entity.constantpool

open class MethodRefInfo(tag: UShort, classIndex: UShort, nameAndTypeIndex: UShort) : BaseConstantPoolItem(tag) {
    val classIndex: UShort
    val nameAndTypeIndex: UShort

    init {
        this.classIndex = classIndex
        this.nameAndTypeIndex = nameAndTypeIndex
    }

    override fun toString(): String {
        return "MethodRefInfo(classIndex=$classIndex, nameAndTypeIndex=$nameAndTypeIndex) ${super.toString()}"
    }
}
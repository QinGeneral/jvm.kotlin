package com.elements.jvmbygo.classfile.entity.constantpool

class InterfaceMethodRefInfo(tag: UShort, classIndex: UShort, nameAndTypeIndex: UShort) : MethodRefInfo(
    tag,
    classIndex,
    nameAndTypeIndex
) {
    override fun toString(): String {
        return "InterfaceMethodRefInfo() ${super.toString()}"
    }
}
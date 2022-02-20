package com.elements.jvmbygo.classfile.entity.constantpool

class FieldRefInfo(tag: UShort, classIndex: UShort, nameAndTypeIndex: UShort) : MethodRefInfo(
    tag,
    classIndex,
    nameAndTypeIndex
) {

    override fun toString(): String {
        return "FieldRefInfo() ${super.toString()}"
    }
}
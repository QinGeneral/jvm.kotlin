package com.elements.jvmbykotlin.classfile.entity.constantpool

class MethodTypeInfo(tag: UShort, val descriptorIndex: Int) : BaseConstantPoolItem(tag) {
    override fun toString(): String {
        return "MethodTypeInfo(descriptorIndex=$descriptorIndex)"
    }
}
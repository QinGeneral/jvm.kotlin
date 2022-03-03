package com.elements.jvmbykotlin.classfile.entity.constantpool

class InvokeDynamicInfo(tag: UShort, var bootstrapMethodAttrIndex: Int, val nameAndTypeIndex: Int) :
    BaseConstantPoolItem(tag) {
    override fun toString(): String {
        return "InvokeDynamicInfo(bootstrapMethodAttrIndex=$bootstrapMethodAttrIndex, nameAndTypeIndex=$nameAndTypeIndex)"
    }
}
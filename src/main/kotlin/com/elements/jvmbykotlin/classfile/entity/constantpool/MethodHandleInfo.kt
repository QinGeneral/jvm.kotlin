package com.elements.jvmbykotlin.classfile.entity.constantpool

class MethodHandleInfo(tag: UShort, val referenceKind: Int, val referenceIndex: Int) : BaseConstantPoolItem(tag) {
    override fun toString(): String {
        return "MethodHandleInfo(referenceKind=$referenceKind, referenceIndex=$referenceIndex)"
    }
}
package com.elements.jvmbygo.classfile.entity.constantpool

open class BaseConstantPoolItem(tag: UShort) {
    protected val tag: UShort

    init {
        this.tag = tag
    }

    override fun toString(): String {
        return "BaseConstantPoolItem(tag=$tag)"
    }
}
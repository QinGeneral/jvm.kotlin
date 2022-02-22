package com.elements.jvmbygo.classfile.entity.attribute

import com.elements.jvmbygo.classfile.entity.AttributeInfo

class SyntheticAttribute(override val nameIndex: UShort, override val length: UInt) :
    AttributeInfo(nameIndex, length) {

    override fun toString(): String {
        return "SyntheticAttribute(nameIndex=$nameIndex, length=$length)"
    }
}
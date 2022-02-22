package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

class DeprecatedAttribute(override val nameIndex: UShort, override val length: UInt) :
    AttributeInfo(nameIndex, length) {

    override fun toString(): String {
        return "DeprecatedAttribute(nameIndex=$nameIndex, length=$length)"
    }
}
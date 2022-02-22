package com.elements.jvmbygo.classfile.entity.attribute

import com.elements.jvmbygo.classfile.ClassReader
import com.elements.jvmbygo.classfile.entity.AttributeInfo

class ConstantValueAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader
) :
    AttributeInfo(nameIndex, length) {
    val constantValueIndex: UShort

    init {
        constantValueIndex = classReader.readU2()
    }

    override fun toString(): String {
        return "ConstantValueAttribute(nameIndex=$nameIndex, length=$length, constantValueIndex=$constantValueIndex)"
    }
}
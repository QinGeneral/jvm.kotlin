package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

class SourceFileAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader
) : AttributeInfo(nameIndex, length) {
    val sourcefileIndex: UShort

    init {
        sourcefileIndex = classReader.readU2()
    }

    override fun toString(): String {
        return "SourceFileAttribute(nameIndex=$nameIndex, length=$length, sourcefileIndex=$sourcefileIndex)"
    }
}
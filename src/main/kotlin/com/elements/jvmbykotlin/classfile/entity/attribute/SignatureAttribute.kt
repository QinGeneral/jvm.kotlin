package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

class SignatureAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader
) : AttributeInfo(nameIndex, length) {
    val signatureIndex: Int

    init {
        signatureIndex = classReader.readU2().toInt()
    }

    override fun toString(): String {
        return "SignatureAttribute(nameIndex=$nameIndex, length=$length, signatureIndex=$signatureIndex)"
    }
}
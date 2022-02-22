package com.elements.jvmbygo.classfile.entity.attribute

import com.elements.jvmbygo.classfile.ClassReader
import com.elements.jvmbygo.classfile.entity.AttributeInfo

class ExceptionsAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader
) : AttributeInfo(nameIndex, length) {
    val numberOfExceptions: UShort
    val exceptionIndexTable: ArrayList<UShort> = ArrayList()

    init {
        numberOfExceptions = classReader.readU2()
        for (i in 0 until numberOfExceptions.toInt()) {
            exceptionIndexTable.add(classReader.readU2())
        }
    }

    override fun toString(): String {
        return "ExceptionsAttribute(nameIndex=$nameIndex, length=$length, numberOfExceptions=$numberOfExceptions, exceptionIndexTable=$exceptionIndexTable)"
    }
}
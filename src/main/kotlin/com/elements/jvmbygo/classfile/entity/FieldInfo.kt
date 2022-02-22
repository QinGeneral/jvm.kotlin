package com.elements.jvmbygo.classfile.entity

import com.elements.jvmbygo.classfile.ClassReader
import com.elements.jvmbygo.classfile.entity.constantpool.ConstantPool

open class FieldInfo(
    classReader: ClassReader,
    cp: ConstantPool
) {
    val accessFlags: UShort
    val nameIndex: UShort
    val descriptorIndex: UShort
    val attributesCount: UShort
    val attributes: ArrayList<AttributeInfo> = ArrayList()

    init {
        accessFlags = classReader.readU2()
        nameIndex = classReader.readU2()
        descriptorIndex = classReader.readU2()
        attributesCount = classReader.readU2()
        for (j in 0 until attributesCount.toInt()) {
            attributes.add(AttributeInfo.of(classReader, cp))
        }
    }

    override fun toString(): String {
        return "FieldInfo(accessFlags=$accessFlags, nameIndex=$nameIndex, descriptorIndex=$descriptorIndex, attributesCount=$attributesCount, attributes=$attributes)"
    }
}
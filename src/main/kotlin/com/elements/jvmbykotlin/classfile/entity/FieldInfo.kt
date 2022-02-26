package com.elements.jvmbykotlin.classfile.entity

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.attribute.CodeAttribute
import com.elements.jvmbykotlin.classfile.entity.constantpool.ConstantPool
import com.elements.jvmbykotlin.classfile.entity.constantpool.Utf8Info

open class FieldInfo(
    classReader: ClassReader,
    cp: ConstantPool
) {
    val accessFlags: UShort
    val nameIndex: UShort
    val descriptorIndex: UShort
    val attributesCount: UShort
    val attributes: ArrayList<AttributeInfo> = ArrayList()

    val name: String
    val descriptor: String

    init {
        accessFlags = classReader.readU2()
        nameIndex = classReader.readU2()
        descriptorIndex = classReader.readU2()
        attributesCount = classReader.readU2()
        for (j in 0 until attributesCount.toInt()) {
            attributes.add(AttributeInfo.of(classReader, cp))
        }

        name = (cp.getItem(nameIndex.toInt()) as Utf8Info).value
        descriptor = (cp.getItem(descriptorIndex.toInt()) as Utf8Info).value
    }

    fun getCodeAttribute(): CodeAttribute? {
        for (attribute in attributes) {
            if (attribute is CodeAttribute) {
                return attribute
            }
        }
        return null
    }

    override fun toString(): String {
        return "FieldInfo(accessFlags=$accessFlags, nameIndex=$nameIndex, descriptorIndex=$descriptorIndex, attributesCount=$attributesCount, attributes=$attributes)"
    }
}
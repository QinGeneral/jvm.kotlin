package com.elements.jvmbykotlin.classfile.entity

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.attribute.ConstantValueAttribute
import com.elements.jvmbykotlin.classfile.entity.constantpool.ConstantPool

open class FieldInfo(
    classReader: ClassReader,
    cp: ConstantPool
) : MemberInfo(classReader, cp) {

    fun getConstantValueAttribute(): ConstantValueAttribute? {
        val constantValueAttributes = attributes
            .filterIsInstance<ConstantValueAttribute>()
        return if (constantValueAttributes.isEmpty()) {
            null
        } else {
            constantValueAttributes.first()
        }
    }

    override fun toString(): String {
        return "FieldInfo(accessFlags=$accessFlags, nameIndex=$nameIndex, descriptorIndex=$descriptorIndex, attributesCount=$attributesCount, attributes=$attributes)"
    }
}
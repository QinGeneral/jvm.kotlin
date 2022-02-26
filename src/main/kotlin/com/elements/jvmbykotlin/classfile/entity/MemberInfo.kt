package com.elements.jvmbykotlin.classfile.entity

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.constantpool.ConstantPool
import com.elements.jvmbykotlin.classfile.entity.constantpool.Utf8Info

open class MemberInfo(
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
            println("memberinfo $accessFlags $nameIndex")
            println((cp.getItem(nameIndex.toInt()) as Utf8Info).value)
            attributes.add(AttributeInfo.of(classReader, cp))
        }

        name = (cp.getItem(nameIndex.toInt()) as Utf8Info).value
        descriptor = (cp.getItem(descriptorIndex.toInt()) as Utf8Info).value
    }
}
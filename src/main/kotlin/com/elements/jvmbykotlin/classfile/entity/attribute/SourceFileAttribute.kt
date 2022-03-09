package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo
import com.elements.jvmbykotlin.classfile.entity.constantpool.ConstantPool
import com.elements.jvmbykotlin.classfile.entity.constantpool.Utf8Info
import com.elements.jvmbykotlin.runtimedata.heap.YuConstantPool

class SourceFileAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader
) : AttributeInfo(nameIndex, length) {
    val sourcefileIndex: UShort

    fun getFileName(constantPool: ConstantPool): String {
        return (constantPool.getItem(sourcefileIndex.toInt()) as Utf8Info).value
    }

    init {
        sourcefileIndex = classReader.readU2()
    }

    override fun toString(): String {
        return "SourceFileAttribute(nameIndex=$nameIndex, length=$length, sourcefileIndex=$sourcefileIndex)"
    }
}
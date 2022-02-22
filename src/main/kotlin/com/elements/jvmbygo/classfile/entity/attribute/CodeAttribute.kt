package com.elements.jvmbygo.classfile.entity.attribute

import com.elements.jvmbygo.classfile.ClassReader
import com.elements.jvmbygo.classfile.entity.AttributeInfo
import com.elements.jvmbygo.classfile.entity.constantpool.ConstantPool

class CodeAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
    cp: ConstantPool
) : AttributeInfo(nameIndex, length) {

    val maxStack: UShort
    val maxLocals: UShort
    val codeLength: UInt
    val code: ByteArray
    val exceptionTableLength: UShort
    val exceptionTable: ArrayList<ExceptionTableItem> = ArrayList()
    val attributeCount: UShort
    val attributes: ArrayList<AttributeInfo> = ArrayList()

    init {
        maxStack = classReader.readU2()
        maxLocals = classReader.readU2()
        codeLength = classReader.readU4()
        code = classReader.readByteArray(codeLength.toInt())
        exceptionTableLength = classReader.readU2()
        for (i in 0 until exceptionTableLength.toInt()) {
            exceptionTable.add(ExceptionTableItem(classReader))
        }
        attributeCount = classReader.readU2()
        for (j in 0 until attributeCount.toInt()) {
            attributes.add(of(classReader, cp))
        }
    }

    class ExceptionTableItem(
        classReader: ClassReader
    ) {
        val startPC: UShort
        val endPC: UShort
        val handlerPC: UShort
        val catchType: UShort

        init {
            startPC = classReader.readU2()
            endPC = classReader.readU2()
            handlerPC = classReader.readU2()
            catchType = classReader.readU2()
        }

        override fun toString(): String {
            return "ExceptionTableItem(startPC=$startPC, endPC=$endPC, handlerPC=$handlerPC, catchType=$catchType)"
        }
    }

    override fun toString(): String {
        return "CodeAttribute(nameIndex=$nameIndex, length=$length, maxStack=$maxStack, maxLocals=$maxLocals, codeLength=$codeLength, code=${code.contentToString()}, exceptionTableLength=$exceptionTableLength, exceptionTable=$exceptionTable, attributeCount=$attributeCount, attributes=$attributes)"
    }
}
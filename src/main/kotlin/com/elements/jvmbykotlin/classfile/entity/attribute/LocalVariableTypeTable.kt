package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

class LocalVariableTypeTable(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) : AttributeInfo(nameIndex, length) {
    val localVariableTypeTableLength: Int
    var localVariableTypeTable: ArrayList<LocalVariableTypeTableItem> = ArrayList()

    init {
        localVariableTypeTableLength = classReader.readU2().toInt()
        for (i in 0 until localVariableTypeTableLength) {
            localVariableTypeTable.add(LocalVariableTypeTableItem(classReader))
        }
    }

    class LocalVariableTypeTableItem(
        classReader: ClassReader
    ) {
        val startPC: Int
        val length: Int
        val nameIndex: Int
        val signatureIndex: Int
        val index: Int

        init {
            startPC = classReader.readU2().toInt()
            length = classReader.readU2().toInt()
            nameIndex = classReader.readU2().toInt()
            signatureIndex = classReader.readU2().toInt()
            index = classReader.readU2().toInt()
        }

        override fun toString(): String {
            return "LocalVariableTypeTableItem(startPC=$startPC, length=$length, nameIndex=$nameIndex, signatureIndex=$signatureIndex, index=$index)"
        }
    }
}
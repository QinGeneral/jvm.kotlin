package com.elements.jvmbygo.classfile.entity.attribute

import com.elements.jvmbygo.classfile.ClassReader
import com.elements.jvmbygo.classfile.entity.AttributeInfo

/**
 *
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.13
 * LocalVariableTable_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 local_variable_table_length;
{   u2 start_pc;
u2 length;
u2 name_index;
u2 descriptor_index;
u2 index;
} local_variable_table[local_variable_table_length];
}
 */
class LocalVariableTableAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) : AttributeInfo(nameIndex, length) {
    val localVariableTableLength: UShort
    val localVariableTable: ArrayList<LocalVariableTableItem> = ArrayList()

    init {
        localVariableTableLength = classReader.readU2()
        for (i in 0 until localVariableTableLength.toInt()) {
            localVariableTable.add(LocalVariableTableItem(classReader))
        }
    }

    class LocalVariableTableItem(
        classReader: ClassReader
    ) {
        val startPC: UShort
        val length: UShort
        val nameIndex: UShort
        val descriptorIndex: UShort
        val index: UShort

        init {
            startPC = classReader.readU2()
            length = classReader.readU2()
            nameIndex = classReader.readU2()
            descriptorIndex = classReader.readU2()
            index = classReader.readU2()
        }

        override fun toString(): String {
            return "LocalVariableTableItem(startPC=$startPC, length=$length, nameIndex=$nameIndex, descriptorIndex=$descriptorIndex, index=$index)"
        }
    }

    override fun toString(): String {
        return "LocalVariableTableAttribute(nameIndex=$nameIndex, length=$length, localVariableTableLength=$localVariableTableLength, localVariableTable=$localVariableTable)"
    }
}
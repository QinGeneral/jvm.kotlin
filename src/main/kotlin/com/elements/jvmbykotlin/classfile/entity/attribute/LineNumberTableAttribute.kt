package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

/**
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.12
 *
 */
//LineNumberTable_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 line_number_table_length;
//    {   u2 start_pc;
//        u2 line_number;
//    } line_number_table[line_number_table_length];
//}
class LineNumberTableAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) : AttributeInfo(nameIndex, length) {
    val lineNumberTableLength: UShort
    val lineNumberTable: ArrayList<LineNumberTableItem> = ArrayList()

    init {
        lineNumberTableLength = classReader.readU2()
        for (i in 0 until lineNumberTableLength.toInt()) {
            lineNumberTable.add(LineNumberTableItem(classReader))
        }
    }

    fun getLineNumber(pc: Int): Int {
        for (i in (lineNumberTableLength.toInt() - 1) downTo 0) {
            val item = lineNumberTable[i]
            if (pc >= item.startPC.toInt()) {
                return item.lineNumber.toInt()
            }
        }
        return -1
    }

    class LineNumberTableItem(
        classReader: ClassReader
    ) {
        val startPC: UShort
        val lineNumber: UShort

        init {
            startPC = classReader.readU2()
            lineNumber = classReader.readU2()
        }

        override fun toString(): String {
            return "LineNumberTableItem(startPC=$startPC, lineNumber=$lineNumber)"
        }
    }

    override fun toString(): String {
        return "LineNumberTableAttribute(nameIndex=$nameIndex, length=$length, lineNumberTableLength=$lineNumberTableLength, lineNumberTable=$lineNumberTable)"
    }
}
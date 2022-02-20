package com.elements.jvmbygo.classfile.entity.attribute

import com.elements.jvmbygo.classfile.ClassReader

//todo
class StackMapTableAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) : BaseAttributeItem(nameIndex, length) {
    val numberOfEntries: UShort
    val entries: ArrayList<StackMapTableItem> = ArrayList()

    init {
        numberOfEntries = classReader.readU2()
        println("number of entries $numberOfEntries")
        for (i in 0 until numberOfEntries.toInt()) {

        }
    }

    class StackMapTableItem {

    }

    override fun toString(): String {
        return "StackMapTableAttribute(nameIndex=$nameIndex, length=$length, numberOfEntries=$numberOfEntries, entries=$entries)"
    }
}
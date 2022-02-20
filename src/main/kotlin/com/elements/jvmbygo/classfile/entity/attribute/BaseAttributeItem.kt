package com.elements.jvmbygo.classfile.entity.attribute

import com.elements.jvmbygo.classfile.ClassReader
import com.elements.jvmbygo.classfile.entity.constantpool.ConstantPool
import com.elements.jvmbygo.classfile.entity.constantpool.Utf8Info

open class BaseAttributeItem(open val nameIndex: UShort, open val length: UInt) {

    init {

    }

    companion object {
        fun of(classReader: ClassReader, cp: ConstantPool): BaseAttributeItem {
            val attrNameIndex: UShort = classReader.readU2()
            val attrName = (cp.getItem(attrNameIndex.toInt()) as Utf8Info).value
            println("attribute name $attrName")
            val attrLength = classReader.readU4()
            when (attrName) {
                "Code" -> {
                    return CodeAttribute(attrNameIndex, attrLength, classReader, cp)
                }
                "LineNumberTable" -> {
                    return LineNumberTableAttribute(attrNameIndex, attrLength, classReader)
                }
                "LocalVariableTable" -> {
                    return LocalVariableTableAttribute(attrNameIndex, attrLength, classReader)
                }
                "StackMapTable"-> {
                    return StackMapTableAttribute(attrNameIndex, attrLength, classReader)
                }
            }
            return BaseAttributeItem(attrNameIndex, attrLength)
        }
    }

    override fun toString(): String {
        return "BaseAttributeItem(nameIndex=$nameIndex, length=$length)"
    }
}
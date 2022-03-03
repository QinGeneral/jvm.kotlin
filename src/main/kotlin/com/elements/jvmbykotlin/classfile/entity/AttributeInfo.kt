package com.elements.jvmbykotlin.classfile.entity

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.attribute.*
import com.elements.jvmbykotlin.classfile.entity.constantpool.ConstantPool
import com.elements.jvmbykotlin.classfile.entity.constantpool.Utf8Info

open class AttributeInfo(open val nameIndex: UShort, open val length: UInt) {

    init {

    }

    companion object {
        fun of(classReader: ClassReader, cp: ConstantPool): AttributeInfo {
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
                "StackMapTable" -> {
                    return StackMapTableAttribute(attrNameIndex, attrLength, classReader)
                }
                "Deprecated" -> {
                    return DeprecatedAttribute(attrNameIndex, attrLength)
                }
                "RuntimeVisibleAnnotations" -> {
                    return RuntimeVisibleAnnotationsAttribute(attrNameIndex, attrLength, classReader)
                }
                "Synthetic" -> {
                    return SyntheticAttribute(attrNameIndex, attrLength)
                }
                "SourceFile" -> {
                    return SourceFileAttribute(attrNameIndex, attrLength, classReader)
                }
                "ConstantValue" -> {
                    return ConstantValueAttribute(attrNameIndex, attrLength, classReader)
                }
                "Exceptions" -> {
                    return ExceptionsAttribute(attrNameIndex, attrLength, classReader)
                }
                "Signature" -> {
                    return SignatureAttribute(attrNameIndex, attrLength, classReader)
                }
                "InnerClasses" -> {
                    return InnerClassesAttribute(attrNameIndex, attrLength, classReader)
                }
                "BootstrapMethods" -> {
                    return BootstrapMethodsAttribute(attrNameIndex, attrLength, classReader)
                }
            }
            return AttributeInfo(attrNameIndex, attrLength)
        }
    }

    override fun toString(): String {
        return "BaseAttributeItem(nameIndex=$nameIndex, length=$length)"
    }
}
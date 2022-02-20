package com.elements.jvmbygo.classfile

import com.elements.jvmbygo.classfile.entity.*
import com.elements.jvmbygo.classfile.entity.attribute.Attribute
import com.elements.jvmbygo.classfile.entity.attribute.BaseAttributeItem
import com.elements.jvmbygo.classfile.entity.attribute.CodeAttribute
import com.elements.jvmbygo.classfile.entity.constantpool.*

class ClassFile {
    // u4
    lateinit var magic: String

    // u2
    var minorVersion: UShort = 0u

    // u2
    var majorVersion: UShort = 0u

    // u2
    var constantPoolCount: UShort = 0u

    lateinit var constantPool: ConstantPool

    // u2
    var accessFlags: UShort = 0u

    // u2
    var thisClass: UShort = 0u

    // u2
    var superClass: UShort = 0u

    // u2
    var interfacesCount: UShort = 0u

    var interfaces: ArrayList<ClassInfo> = ArrayList()

    // u2
    var fieldsCount: UShort = 0u

    var fieldsInfo: ArrayList<FieldInfo> = ArrayList()

    // u2
    var methodsCount: UShort = 0u

    var methods: ArrayList<MethodInfo> = ArrayList()

    // u2
    var attributesCount: UShort = 0u

    var attributes: ArrayList<AttributesInfoItem> = ArrayList()

    inner class AttributesInfoItem {

    }

    fun parse(classData: ByteArray) {
        val classReader = ClassReader(classData)
        parserMagic(classReader)
        parserVersion(classReader)
        parseConstantPool(classReader)
        accessFlags = classReader.readU2()
        println("accessFlags $accessFlags")
        thisClass = classReader.readU2()
        println("thisClass $thisClass")
        superClass = classReader.readU2()
        println("superClass $superClass")
        parseInterfaces(classReader)
        parseFields(classReader)
        parseMethods(classReader)
    }

    private fun parserVersion(classReader: ClassReader) {
        minorVersion = classReader.readU2()
        majorVersion = classReader.readU2()
        println("version $minorVersion $majorVersion")
        when (majorVersion.toInt()) {
            45 -> {
                return
            }
            in 46..52 -> {
                if (minorVersion.toInt() == 0) {
                    return
                }
            }
        }
        throw UnsupportedClassVersionError("version not support $minorVersion $majorVersion")
    }

    private fun parserMagic(classReader: ClassReader) {
        magic = classReader.readU4().toString(16)
        println("magic $magic")
        if (magic != "cafebabe") {
            throw ClassFormatError("magic not match")
        }
    }

    private fun parseMethods(classReader: ClassReader) {
        methodsCount = classReader.readU2()
        println("methodsCount count $methodsCount")
        for (i in 0 until methodsCount.toInt()) {
            val item = MethodInfo.of(classReader, constantPool)
            methods.add(item)
            println(item.toString() + " " + i + " " + methods.size)
        }
    }

    private fun parseFields(classReader: ClassReader) {
        fieldsCount = classReader.readU2()
        println("fieldsCount count $fieldsCount")
        for (i in 0 until fieldsCount.toInt()) {
            val accessFlags = classReader.readU2()
            val nameIndex = classReader.readU2()
            val descriptorIndex = classReader.readU2()
            val attributesCount = classReader.readU2()
            val attributes: ArrayList<BaseAttributeItem> = ArrayList()
            for (j in 0 until attributesCount.toInt()) {
                val attrNameIndex = classReader.readU2()
                val attrName = attrNameIndex.toString()
                val attrLength = classReader.readU4()
                if (attrName == "Code") {
                    val attributeItem = CodeAttribute(attrNameIndex, attrLength, classReader, constantPool)
                    attributes.add(attributeItem)
                }
            }
            val item = FieldInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes)
            fieldsInfo.add(item)
            println(item.toString() + " " + i + " " + fieldsInfo.size)
        }
    }

    private fun parseInterfaces(classReader: ClassReader) {
        interfacesCount = classReader.readU2()
        println("interfaces count $interfacesCount")
        for (i in 0 until interfacesCount.toInt()) {
            val nameIndex = classReader.readU2()
            val item = ClassInfo(ConstantType.CLASS_INFO.type, nameIndex)
            interfaces.add(item)
            println(item.toString() + " " + i + " " + interfaces.size)
        }
    }

    private fun parseConstantPool(classReader: ClassReader) {
        constantPoolCount = classReader.readU2()
        println("constant pool count $constantPoolCount")
        constantPool = ConstantPool.of(constantPoolCount.toInt(), classReader)
    }

    override fun toString(): String {
        return "ClassFile(magic='$magic', minorVersion=$minorVersion, majorVersion=$majorVersion, constantPoolCount=$constantPoolCount, constantPool=$constantPool, accessFlags=$accessFlags, thisClass=$thisClass, superClass=$superClass, interfacesCount=$interfacesCount, interfaces=$interfaces, fieldsCount=$fieldsCount, fieldsInfo=$fieldsInfo, methodsCount=$methodsCount, methods=$methods, attributesCount=$attributesCount, attributes=$attributes)"
    }
}

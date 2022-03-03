package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

class BootstrapMethodsAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) : AttributeInfo(nameIndex, length) {
    val numberOfBootStrapMethods: Int
    val bootStrapMethods: ArrayList<BootStrapMethod> = ArrayList()

    init {
        numberOfBootStrapMethods = classReader.readU2().toInt()
        for (i in 0 until numberOfBootStrapMethods) {
            bootStrapMethods.add(BootStrapMethod(classReader))
        }
    }

    class BootStrapMethod(classReader: ClassReader) {
        val bootStrapMethodRef: Int
        val numberOfBootStrapArguments: Int
        val bootStrapArguments: ArrayList<Int> = ArrayList()

        init {
            bootStrapMethodRef = classReader.readU2().toInt()
            numberOfBootStrapArguments = classReader.readU2().toInt()
            for (i in 0 until numberOfBootStrapArguments) {
                bootStrapArguments.add(classReader.readU2().toInt())
            }
        }

        override fun toString(): String {
            return "BootStrapMethod(bootStrapMethodRef=$bootStrapMethodRef, numberOfBootStrapArguments=$numberOfBootStrapArguments, bootStrapArguments=$bootStrapArguments)"
        }
    }

    override fun toString(): String {
        return "BootstrapMethodsAttribute(nameIndex=$nameIndex, length=$length, numberOfBootStrapMethods=$numberOfBootStrapMethods, bootStrapMethods=$bootStrapMethods)"
    }
}
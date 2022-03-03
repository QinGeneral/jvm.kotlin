package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

class InnerClassesAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) : AttributeInfo(nameIndex, length) {
    val numberOfClasses: Int
    val classes: ArrayList<InnerClass> = ArrayList()

    init {
        numberOfClasses = classReader.readU2().toInt()
        for (i in 0 until numberOfClasses) {
            classes.add(InnerClass(classReader))
        }
    }

    class InnerClass(classReader: ClassReader) {
        val innerClassInfoIndex: Int
        val outerClassInfoIndex: Int
        val innerNameIndex: Int
        val innerClassAccessFlags: Int

        init {
            innerClassInfoIndex = classReader.readU2().toInt()
            outerClassInfoIndex = classReader.readU2().toInt()
            innerNameIndex = classReader.readU2().toInt()
            innerClassAccessFlags = classReader.readU2().toInt()
        }

        override fun toString(): String {
            return "InnerClass(innerClassInfoIndex=$innerClassInfoIndex, outerClassInfoIndex=$outerClassInfoIndex, innerNameIndex=$innerNameIndex, innerClassAccessFlags=$innerClassAccessFlags)"
        }
    }

    override fun toString(): String {
        return "InnerClasses(nameIndex=$nameIndex, length=$length, numberOfClasses=$numberOfClasses, classes=$classes)"
    }
}
package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

class RuntimeVisibleAnnotationsAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) :
    AttributeInfo(nameIndex, length) {
    val numOfAnnotations: UShort
    val annotations: ArrayList<AnnotationItem> = ArrayList()

    init {
        numOfAnnotations = classReader.readU2()
        for (i in 0 until numOfAnnotations.toInt()) {
            annotations.add(AnnotationItem(classReader))
        }
    }

    class AnnotationItem(classReader: ClassReader) {
        val typeIndex: UShort
        val numOfElementValuePairs: UShort

        init {
            typeIndex = classReader.readU2()
            numOfElementValuePairs = classReader.readU2()
        }

        override fun toString(): String {
            return "AnnotationItem(typeIndex=$typeIndex, numOfElementValuePairs=$numOfElementValuePairs)"
        }

        // todo
//        val elementValuePairs: ArrayList<>

//        class Element {
//            val elementNameIndex: UShort;
//
//        }
    }

    override fun toString(): String {
        return "RuntimeVisibleAnnotationsAttribute(nameIndex=$nameIndex, length=$length, numOfAnnotations=$numOfAnnotations, annotations=$annotations)"
    }
}
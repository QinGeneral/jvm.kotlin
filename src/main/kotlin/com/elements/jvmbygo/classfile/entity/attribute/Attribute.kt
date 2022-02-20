package com.elements.jvmbygo.classfile.entity.attribute

class Attribute(val nameIndex: UShort, val length: UInt, val info: ByteArray) {
    val attributeItems: ArrayList<BaseAttributeItem> = ArrayList()
    init {

    }
    override fun toString(): String {
        return "Attribute(nameIndex=$nameIndex, length=$length, info=${info.contentToString()})"
    }
}
package com.elements.jvmbygo.classfile.entity

import com.elements.jvmbygo.classfile.entity.attribute.BaseAttributeItem

open class FieldInfo(
    accessFlags: UShort,
    nameIndex: UShort,
    descriptorIndex: UShort,
    attributesCount: UShort,
    attributes: ArrayList<BaseAttributeItem>
) {
    val accessFlags: UShort
    val nameIndex: UShort
    val descriptorIndex: UShort
    val attributesCount: UShort
    val attributes: ArrayList<BaseAttributeItem>

    init {
        this.accessFlags = accessFlags
        this.nameIndex = nameIndex
        this.descriptorIndex = descriptorIndex
        this.attributesCount = attributesCount
        this.attributes = attributes
    }

    override fun toString(): String {
        return "FieldInfo(accessFlags=$accessFlags, nameIndex=$nameIndex, descriptorIndex=$descriptorIndex, attributesCount=$attributesCount, attributes=$attributes)"
    }
}
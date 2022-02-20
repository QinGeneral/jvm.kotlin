package com.elements.jvmbygo.classfile.entity

import com.elements.jvmbygo.classfile.ClassReader
import com.elements.jvmbygo.classfile.entity.attribute.Attribute
import com.elements.jvmbygo.classfile.entity.attribute.BaseAttributeItem
import com.elements.jvmbygo.classfile.entity.attribute.CodeAttribute
import com.elements.jvmbygo.classfile.entity.constantpool.BaseConstantPoolItem
import com.elements.jvmbygo.classfile.entity.constantpool.ConstantPool
import com.elements.jvmbygo.classfile.entity.constantpool.Utf8Info

/**
 * MethodInfo
 * method_info {
 *     u2             access_flags;
 *     u2             name_index;
 *     u2             descriptor_index;
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.6
 *
 * @author hanzhang
 * @since 2022-02-20
 */
class MethodInfo(
    accessFlags: UShort,
    nameIndex: UShort,
    descriptorIndex: UShort,
    attributesCount: UShort,
    attributes: ArrayList<BaseAttributeItem>
) : FieldInfo(
    accessFlags,
    nameIndex,
    descriptorIndex,
    attributesCount,
    attributes
) {
    companion object {
        fun of(classReader: ClassReader, cp: ConstantPool): MethodInfo {
            val accessFlags: UShort = classReader.readU2()
            val nameIndex: UShort = classReader.readU2()
            val descriptorIndex: UShort = classReader.readU2()
            val attributesCount: UShort = classReader.readU2()
            val attributes: ArrayList<BaseAttributeItem> = ArrayList()
            for (j in 0 until attributesCount.toInt()) {
                attributes.add(BaseAttributeItem.of(classReader, cp))
            }
            return MethodInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes)
        }
    }

    override fun toString(): String {
        return "MethodInfo() ${super.toString()}"
    }
}
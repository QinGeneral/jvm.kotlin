package com.elements.jvmbykotlin.classfile.entity

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.attribute.CodeAttribute
import com.elements.jvmbykotlin.classfile.entity.constantpool.ConstantPool

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
    classReader: ClassReader,
    cp: ConstantPool
) : MemberInfo(
    classReader,
    cp
) {
    fun getCodeAttribute(): CodeAttribute? {
        for (attribute in attributes) {
            if (attribute is CodeAttribute) {
                return attribute
            }
        }
        return null
    }

    override fun toString(): String {
        return "MethodInfo(accessFlags=$accessFlags, nameIndex=$nameIndex, descriptorIndex=$descriptorIndex, attributesCount=$attributesCount, attributes=$attributes)"
    }
}
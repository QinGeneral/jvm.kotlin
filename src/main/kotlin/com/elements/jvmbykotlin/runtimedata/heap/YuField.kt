package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.entity.FieldInfo

/**
 * 字段
 *
 * @author hanzhang
 * @since 2022-02-26
 */
class YuField(yuClass: YuClass, fieldInfo: FieldInfo) : ClassMember(yuClass, fieldInfo) {
    var slotId: Int = 0
    var constValueIndex: Int = 0

    init {
        if (fieldInfo.getConstantValueAttribute() != null) {
            constValueIndex = fieldInfo.getConstantValueAttribute()!!.nameIndex.toInt()
        }
    }

    companion object {
        fun getFields(yuClass: YuClass, fieldInfo: ArrayList<FieldInfo>): Array<YuField> {
            return fieldInfo
                .map { item -> YuField(yuClass, item) }
                .toList()
                .toTypedArray()
        }
    }

    fun isLongOrDouble(): Boolean {
        return (descriptor == "J") or (descriptor == "D")
    }

    fun isAccessibleTo(otherClass: YuClass): Boolean {
        if (isPublic()) {
            return true
        }
        val c = yuClass
        if (isProtected()) {
            return (otherClass == c) or (otherClass.isSubClassOf(c)) or (c.getPackageName() == otherClass.getPackageName())
        }
        if (!isPrivate()) {
            return c.getPackageName() == otherClass.getPackageName()
        }
        return otherClass == c
    }

    override fun toString(): String {
        return "YuField(slotId=$slotId, constValueIndex=$constValueIndex)"
    }
}
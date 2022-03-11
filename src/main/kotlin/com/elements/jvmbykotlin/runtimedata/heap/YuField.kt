package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.entity.FieldInfo

/**
 * Java field
 *
 * @author hanzhang
 */
class YuField(yuClass: YuClass, fieldInfo: FieldInfo) : ClassMember(yuClass, fieldInfo) {
    var slotId: Int = 0
    var constValueIndex: Int = 0

    init {
        if (fieldInfo.getConstantValueAttribute() != null) {
            constValueIndex = fieldInfo.getConstantValueAttribute()!!.constantValueIndex.toInt()
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

    override fun toString(): String {
        return "YuField(slotId=$slotId, constValueIndex=$constValueIndex)"
    }
}
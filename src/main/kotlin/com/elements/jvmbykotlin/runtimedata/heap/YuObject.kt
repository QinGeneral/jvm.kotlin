package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.runtimedata.LocalVariable

open class YuObject(open val yuClass: YuClass) {
    var fields: LocalVariable = LocalVariable()
    var extra: Any? = null

    constructor(yuClass: YuClass, fields: LocalVariable) : this(yuClass) {
        this.fields = fields
    }

    fun getRefVariable(name: String, descriptor: String): YuObject? {
        val field = yuClass.getField(name, descriptor, false)
        val slots = fields
        return slots.getRef(field!!.slotId)
    }

    fun setRefVariable(name: String, descriptor: String, ref: YuObject) {
        val field = yuClass.getField(name, descriptor, false)
        return fields.setRef(field!!.slotId, ref)
    }

    fun isInstanceOf(yuClass: YuClass): Boolean {
        return yuClass.isAssignableFrom(this.yuClass)
    }

    override fun toString(): String {
        return "YuObject(yuClass=${yuClass.name}, fields=${fields.maxLocals})"
    }

    // todo shallow copy
    fun clone(): YuObject {
        return YuObject(yuClass, fields)
    }
}
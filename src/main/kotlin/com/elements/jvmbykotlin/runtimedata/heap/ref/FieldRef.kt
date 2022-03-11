package com.elements.jvmbykotlin.runtimedata.heap.ref

import com.elements.jvmbykotlin.runtimedata.heap.MemberRef
import com.elements.jvmbykotlin.runtimedata.heap.YuClass
import com.elements.jvmbykotlin.runtimedata.heap.YuConstantPool
import com.elements.jvmbykotlin.runtimedata.heap.YuField

/**
 * Field reference
 *
 * @author hanzhang
 */
class FieldRef(
    yuConstantPool: YuConstantPool,
    className: String,
    yuClass: YuClass?,
    name: String,
    descriptor: String,
) : MemberRef(yuConstantPool, className, yuClass, name, descriptor) {
    var field: YuField? = null

    fun resolveField(): YuField {
        if (field == null) {
            resolveFieldRef()
        }
        return field!!
    }

    fun resolveFieldRef() {
        val d = yuConstantPool.yuClass
        val c = resolvedClass()
        val field = lookupField(c, name, descriptor) ?: throw NoSuchFieldError("No such field $name")
        if (!field.isAccessibleTo(d)) {
            throw IllegalAccessError("Field $name can't access to ${d.name}")
        }
        this.field = field
    }

    fun lookupField(yuClass: YuClass, name: String, descriptor: String): YuField? {
        for (field in yuClass.fields) {
            if ((field.name == name) and (field.descriptor == descriptor)) {
                return field
            }
        }
        for (interf in yuClass.interfaces) {
            val field = lookupField(interf, name, descriptor)
            if (field != null) {
                return field
            }
        }
        if (yuClass.superClass != null) {
            return lookupField(yuClass.superClass!!, name, descriptor)
        }
        return null
    }

    override fun toString(): String {
        return "FieldRef(name='$name', descriptor='$descriptor')"
    }
}
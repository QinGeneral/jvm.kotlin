package com.elements.jvmbykotlin.runtimedata.heap

open class SymRef(
    val yuConstantPool: YuConstantPool,
    val className: String,
    var yuClass: YuClass?
) {
    fun resolvedClass(): YuClass {
        if (yuClass == null) {
            resolveClassRef()
        }
        return yuClass!!
    }

    fun resolveClassRef() {
        val d = yuConstantPool.yuClass
        val c = d.loader.loadClass(className)
        if (!c.isAccessibleTo(d)) {
            throw IllegalAccessException("Class $c can't access to ${d.name}")
        }
        yuClass = c
    }

    override fun toString(): String {
        return "SymRef(yuConstantPool=$yuConstantPool, className='$className', yuClass=$yuClass)"
    }
}

class ClassRef(
    yuConstantPool: YuConstantPool,
    className: String,
    yuClass: YuClass
) : SymRef(yuConstantPool, className, yuClass) {

}

open class MemberRef(
    yuConstantPool: YuConstantPool,
    className: String,
    yuClass: YuClass?,
    val name: String,
    val descriptor: String
) : SymRef(yuConstantPool, className, yuClass) {
    override fun toString(): String {
        return "MemberRef(name='$name', descriptor='$descriptor')"
    }
}

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

class MethodRef(
    yuConstantPool: YuConstantPool,
    className: String,
    yuClass: YuClass?,
    name: String,
    descriptor: String,
) : MemberRef(yuConstantPool, className, yuClass, name, descriptor) {
    lateinit var method: YuMethod

    fun resolveMethod() {
//        field = YuField(yuClass)
    }

    override fun toString(): String {
        return "MethodRef(name='$name', descriptor='$descriptor')"
    }
}

class InterfaceMethodRef(
    yuConstantPool: YuConstantPool,
    className: String,
    yuClass: YuClass?,
    name: String,
    descriptor: String,
) : MemberRef(yuConstantPool, className, yuClass, name, descriptor) {
    lateinit var method: YuMethod

    fun resolveMethod() {
//        field = YuField(yuClass)
    }

    override fun toString(): String {
        return "InterfaceMethodRef(method=$method)"
    }
}
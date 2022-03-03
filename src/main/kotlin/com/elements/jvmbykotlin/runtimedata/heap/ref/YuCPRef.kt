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
    yuClass: YuClass?
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
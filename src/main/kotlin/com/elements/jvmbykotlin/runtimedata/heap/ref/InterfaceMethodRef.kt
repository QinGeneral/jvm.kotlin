package com.elements.jvmbykotlin.runtimedata.heap.ref

import com.elements.jvmbykotlin.runtimedata.heap.MemberRef
import com.elements.jvmbykotlin.runtimedata.heap.YuClass
import com.elements.jvmbykotlin.runtimedata.heap.YuConstantPool
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

class InterfaceMethodRef(
    yuConstantPool: YuConstantPool,
    className: String,
    yuClass: YuClass?,
    name: String,
    descriptor: String,
) : MemberRef(yuConstantPool, className, yuClass, name, descriptor) {
    var method: YuMethod? = null

    fun resolveInterfaceMethod(): YuMethod {
        if (method == null) {
            resolveInterfaceMethodRef()
        }
        return method!!
    }

    private fun resolveInterfaceMethodRef() {
        val d = yuConstantPool.yuClass
        val c = resolvedClass()
        if (!c.isInterface()) {
            throw IncompatibleClassChangeError("Current ${c.name} is not interface")
        }
        val method = lookupInterfaceMethod(c, name, descriptor) ?: throw NoSuchFieldError("No such method")
        if (!method.isAccessibleTo(d)) {
            throw  IllegalAccessError("Method ${method.name} can't access to class ${d.name}")
        }
        this.method = method
    }

    private fun lookupInterfaceMethod(interfaces: YuClass, name: String, descriptor: String): YuMethod? {
        for (method in interfaces.methods) {
            if ((method.name == name) and (method.descriptor == descriptor)) {
                return method
            }
        }
        return MethodRef.lookupMethodInInterfaces(interfaces.interfaces, name, descriptor)
    }

    override fun toString(): String {
        return "InterfaceMethodRef(method=$method)"
    }
}
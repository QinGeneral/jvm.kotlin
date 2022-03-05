package com.elements.jvmbykotlin.runtimedata.heap.ref

import com.elements.jvmbykotlin.runtimedata.heap.MemberRef
import com.elements.jvmbykotlin.runtimedata.heap.YuClass
import com.elements.jvmbykotlin.runtimedata.heap.YuConstantPool
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

class MethodRef(
    yuConstantPool: YuConstantPool,
    className: String,
    yuClass: YuClass?,
    name: String,
    descriptor: String,
) : MemberRef(yuConstantPool, className, yuClass, name, descriptor) {
    var method: YuMethod? = null

    fun resolveMethod(): YuMethod {
        if (method == null) {
            resolveMethodRef()
        }
        return method!!
    }

    fun resolveMethodRef() {
        val d = yuConstantPool.yuClass
        val c = resolvedClass()
        if (c.isInterface()) {
            throw IncompatibleClassChangeError("Current ${c.name} is interface")
        }
        val method = lookupMethod(c, name, descriptor) ?: throw NoSuchFieldError("No such method")
        if (!method.isAccessibleTo(d)) {
            throw  IllegalAccessError("Method ${c.name} ${method.name} can't access to class ${d.name}")
        }
        this.method = method
    }

    fun lookupMethod(yuClass: YuClass, name: String, descriptor: String): YuMethod? {
        var method = lookupMethodInClass(yuClass, name, descriptor)
        if (method == null) {
            method = lookupMethodInInterfaces(yuClass.interfaces, name, descriptor)
        }
        return method
    }

    companion object {
        fun lookupMethodInClass(yuClass: YuClass?, name: String, descriptor: String): YuMethod? {
            var c: YuClass? = yuClass
            while (c != null) {
                for (method in c.methods) {
                    if ((method.name == name) and (method.descriptor == descriptor)) {
                        return method
                    }
                }
                c = c.superClass
            }
            return null
        }

        fun lookupMethodInInterfaces(interfaces: ArrayList<YuClass>, name: String, descriptor: String): YuMethod? {
            for (interf in interfaces) {
                for (method in interf.methods) {
                    if ((method.name == name) and (method.descriptor == descriptor)) {
                        return method
                    }
                }
                val method = lookupMethodInInterfaces(interf.interfaces, name, descriptor)
                if (method != null) {
                    return method
                }
            }
            return null
        }
    }

    override fun toString(): String {
        return "MethodRef(name='$name', descriptor='$descriptor')"
    }
}

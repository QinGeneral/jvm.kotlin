package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.ClassFile
import com.elements.jvmbykotlin.runtimedata.LocalVariable

open class YuClass(classFile: ClassFile) {
    val accessFlags: Int
    val name: String
    val superClassName: String
    val interfaceNames: Array<String>

    val constantPool: YuConstantPool
    val fields: Array<YuField>
    val methods: Array<YuMethod>
    lateinit var loader: YuClassLoader
    var superClass: YuClass? = null
    val interfaces: ArrayList<YuClass> = ArrayList()

    var instanceSlotCount: Int = 0
    var staticSlotCount: Int = 0

    var staticVariables: LocalVariable = LocalVariable()

    init {
        accessFlags = classFile.accessFlags.toInt()
        name = classFile.className
        superClassName = classFile.superClassName
        interfaceNames = classFile.interfaceNames.toTypedArray()
        constantPool = YuConstantPool(this, classFile.constantPool)
        fields = YuField.getFields(this, classFile.fieldsInfo)
        methods = YuMethod.getMethods(this, classFile.methods)
    }

    fun createObject(yuClass: YuClass): YuObject {
        return YuObject(yuClass, LocalVariable(yuClass.instanceSlotCount))
    }

    fun getMainMethod(): YuMethod? {
        return getStaticMethod("main", "([Ljava/lang/String;)V")
    }

    fun getStaticMethod(name: String, descriptor: String): YuMethod? {
        for (method in methods) {
            if (method.isStatic() and (method.name == name) and (method.descriptor == descriptor)) {
                return method
            }
        }
        return null
    }

    fun getPackageName(): String {
        val index = name.lastIndexOf("/")
        if (index < 0) {
            return ""
        }
        return name.substring(index)
    }

    fun isAssignableFrom(otherClass: YuClass): Boolean {
        if (otherClass == this) {
            return true
        }
        return if (!isInterface()) {
            otherClass.isSubClassOf(this)
        } else {
            otherClass.isImplements(this)
        }
    }

    fun isImplements(interf: YuClass): Boolean {
        var c: YuClass? = this
        while (c != null) {
            for (i in c.interfaces) {
                if ((i == interf) or i.isSubInterfaceOf(interf)) {
                    return true
                }
            }
            c = c.superClass
        }
        return false
    }

    fun isSubInterfaceOf(interf: YuClass): Boolean {
        for (superInterface in interfaces) {
            if ((superInterface == interf) or (superInterface.isSubInterfaceOf(interf))) {
                return true
            }
        }
        return false
    }

    fun isSubClassOf(otherClass: YuClass): Boolean {
        var c = superClass
        while (c != null) {
            if (c == otherClass) {
                return true
            }
            c = c.superClass
        }
        return false
    }

    fun isSuperClassOf(otherClass: YuClass): Boolean {
        return otherClass.isSubClassOf(this)
    }

    fun isAccessibleTo(otherClass: YuClass): Boolean {
        return isPublic() or (getPackageName() == otherClass.getPackageName())
    }

    fun isPublic(): Boolean {
        return (accessFlags and AccessFlagType.ACC_PUBLIC.value) != 0
    }

    fun isFinal(): Boolean {
        return (accessFlags and AccessFlagType.ACC_FINAL.value) != 0
    }

    fun isSuper(): Boolean {
        return (accessFlags and AccessFlagType.ACC_SUPER.value) != 0
    }

    fun isInterface(): Boolean {
        return (accessFlags and AccessFlagType.ACC_INTERFACE.value) != 0
    }

    fun isAbstract(): Boolean {
        return (accessFlags and AccessFlagType.ACC_ABSTRACT.value) != 0
    }

    fun isSynthetic(): Boolean {
        return (accessFlags and AccessFlagType.ACC_SYNTHETIC.value) != 0
    }

    fun isAnnotation(): Boolean {
        return (accessFlags and AccessFlagType.ACC_ANNOTATION.value) != 0
    }

    fun isEnum(): Boolean {
        return (accessFlags and AccessFlagType.ACC_ENUM.value) != 0
    }

    override fun toString(): String {
        return "YuClass(accessFlags=$accessFlags, name='$name', superClassName='$superClassName', interfaceNames=${interfaceNames.contentToString()}, constantPool=$constantPool, fields=${fields.contentToString()}, methods=${methods.contentToString()}, loader=$loader, superClass=$superClass, interfaces=$interfaces, instanceSlotCount=$instanceSlotCount, staticSlotCount=$staticSlotCount, staticVariables=$staticVariables)"
    }
}
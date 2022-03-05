package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.ClassFile
import com.elements.jvmbykotlin.runtimedata.LocalVariable

open class YuClass() {
    var accessFlags: Int = 0
    var name: String = ""
    var superClassName: String = ""
    var interfaceNames: Array<String> = Array(0) { "" }

    lateinit var constantPool: YuConstantPool
    lateinit var fields: Array<YuField>
    var methods: ArrayList<YuMethod> = ArrayList()
    lateinit var loader: YuClassLoader
    var superClass: YuClass? = null
    val interfaces: ArrayList<YuClass> = ArrayList()

    var instanceSlotCount: Int = 0
    var staticSlotCount: Int = 0

    var staticVariables: LocalVariable = LocalVariable()

    var isInitStarted = false

    var jClass : YuObject? = null

    val javaName:String
        get() {
            return name.replace("/", ".")
        }

    constructor(classFile: ClassFile) : this() {
        this.accessFlags = classFile.accessFlags.toInt()
        this.name = classFile.className
        this.superClassName = classFile.superClassName
        this.interfaceNames = classFile.interfaceNames.toTypedArray()
        this.constantPool = YuConstantPool(this, classFile.constantPool)
        this.fields = YuField.getFields(this, classFile.fieldsInfo)
        this.methods = YuMethod.getMethods(this, classFile.methods)
    }

    constructor(
        accessFlag: Int,
        name: String,
        yuClassLoader: YuClassLoader,
        initStarted: Boolean,
        superClass: YuClass?,
        interfaces: ArrayList<YuClass>
    ) : this() {
        this.accessFlags = accessFlag
        this.name = name
        this.loader = yuClassLoader
        this.isInitStarted = initStarted
        this.superClass = superClass
        this.interfaces.addAll(interfaces)
    }

    fun startInit() {
        isInitStarted = true
    }

    fun getClinitMethod(): YuMethod? {
        return getStaticMethod("<clinit>", "()V")
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
        return name.substring(0, index)
    }

    fun getComponentClass(): YuClass {
        val componentClassName = getComponentClassName(name)
        return loader.loadClass(componentClassName)
    }

    fun getComponentClassName(className: String): String {
        if (className[0] == '[') {
            val componentTypeDescriptor = className.substring(1)
            return toClassName(componentTypeDescriptor)
        }
        throw Exception("Not array $className")
    }

    fun toClassName(descriptor: String): String {
        if (descriptor[0] == '[') {
            return descriptor
        }
        if (descriptor[0] == 'L') {
            return descriptor.substring(1, descriptor.length - 1)
        }
        for (key in Constants.PRIMITIVE_TYPES.keys) {
            if (key == descriptor) {
                return Constants.PRIMITIVE_TYPES[key]!!
            }
        }
        throw Exception("Invalid descriptor $descriptor")
    }

    fun getArrayClass(): YuClass {
        val arrayClassName = getArrayClassName(name)
        return loader.loadClass(arrayClassName)
    }

    private fun getArrayClassName(name: String): String {
        return "[" + toDescriptor(name)
    }

    fun toDescriptor(className: String): String {
        if (className[0] == '[') {
            return className
        }
        if (className in Constants.PRIMITIVE_TYPES.keys) {
            return Constants.PRIMITIVE_TYPES[className]!!
        }
        return "L" + className + ";"
    }

    fun isArray(): Boolean {
        return name[0] == '['
    }

    fun isAssignableFrom(otherClass: YuClass): Boolean {
        if (otherClass == this) {
            return true
        }
        if (otherClass.isArray()) {
            if (!otherClass.isInterface()) {
                if (!isInterface()) {
                    return otherClass.isSubClassOf(this)
                } else {
                    return otherClass.isImplements(this)
                }
            } else {
                if (!isInterface()) {
                    return isJLObject()
                } else {
                    return isSubInterfaceOf(otherClass)
                }
            }
        } else {
            if (!isArray()) {
                if (!isInterface()) {
                    return isJLObject()
                } else {
                    return isJLCloneable() || isJioSerializable()
                }
            } else {
                val sc = otherClass.getComponentClass()
                val tc = getComponentClass()
                return sc == tc || tc.isAssignableFrom(sc)
            }
        }
        return false
    }

    fun isJLObject(): Boolean {
        return name == "java/lang/Object"
    }

    fun isJLCloneable(): Boolean {
        return name == "java/lang/Cloneable"
    }

    fun isJioSerializable(): Boolean {
        return name == "java/io/Serializable"
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

    fun getField(name: String, descriptor: String, isStatic: Boolean): YuField? {
        var c: YuClass? = this
        while (c != null) {
            for (field in c.fields) {
                if ((field.isStatic() == isStatic) && (field.name == name) && field.descriptor == descriptor) {
                    return field
                }
            }
            c = c.superClass
        }
        return null
    }

    override fun toString(): String {
        return "YuClass(accessFlags=$accessFlags, name='$name', superClassName='$superClassName', interfaceNames=${interfaceNames.contentToString()}, loader=$loader, superClass=$superClass, interfaces=$interfaces, instanceSlotCount=$instanceSlotCount, staticSlotCount=$staticSlotCount, staticVariables=$staticVariables)"
    }
}
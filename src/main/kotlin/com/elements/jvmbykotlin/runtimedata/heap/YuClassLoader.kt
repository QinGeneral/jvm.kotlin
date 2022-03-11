package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.ClassFile
import com.elements.jvmbykotlin.classfile.entity.constantpool.IntegerInfo
import com.elements.jvmbykotlin.classpath.ClassReadResult
import com.elements.jvmbykotlin.classpath.Classpath
import com.elements.jvmbykotlin.runtimedata.LocalVariable

/**
 * Java class loader, load byte code to run time class
 *
 * @author hanzhang
 */
class YuClassLoader(
    val classPath: Classpath,
    val isShowLog: Boolean
) {
    private val classMap = HashMap<String, YuClass>()

    init {
        loadBasicClasses()
        loadPrimitiveClasses()
    }

    private fun loadBasicClasses() {
        val jlClassClass = loadClass("java/lang/Class")
        for (c in classMap.values) {
            if (c.jClass == null) {
                c.jClass = YuObject(jlClassClass, LocalVariable(jlClassClass.instanceSlotCount))
                c.jClass!!.extra = c
            }
        }
    }

    private fun loadPrimitiveClasses() {
        for (primitiveType in Constants.PRIMITIVE_TYPES.keys) {
            loadPrimitiveClass(primitiveType)
        }
    }

    private fun loadPrimitiveClass(className: String) {
        val c = YuClass()
        c.accessFlags = AccessFlagType.ACC_PUBLIC.value
        c.name = className
        c.loader = this
        c.startInit()
        val classClass = classMap["java/lang/Class"]!!
        c.jClass = YuObject(classClass, LocalVariable(classClass.instanceSlotCount))
        c.jClass!!.extra = c
        classMap[className] = c
    }

    fun loadClass(name: String): YuClass {
        if (name in classMap.keys) {
            return classMap[name]!!
        }
        val c: YuClass = if (name[0] == '[') {
            loadArrayClass(name)
        } else {
            loadNonArrayClass(name)
        }
        val classClass = classMap["java/lang/Class"]
        if (classClass != null) {
            c.jClass = YuObject(classClass, LocalVariable(classClass.instanceSlotCount))
            c.jClass!!.extra = c
        }
        return c
    }

    private fun loadArrayClass(name: String): YuClass {
        val arrayClass = YuClass(
            // todo
            AccessFlagType.ACC_PUBLIC.value,
            name,
            this,
            true,
            loadClass("java/lang/Object"),
            arrayListOf(loadClass("java/lang/Cloneable"), loadClass("java/io/Serializable"))
        )
        classMap[name] = arrayClass
        return arrayClass
    }

    private fun loadNonArrayClass(name: String): YuClass {
        val classReadResult = readClass(name)
        val yuClass = defClass(classReadResult.byteCode!!)
        link(yuClass)
        if (isShowLog) {
            println("loadNonArrayClass $name")
        }
        return yuClass
    }

    private fun readClass(name: String): ClassReadResult {
        println("readClass name $name")
        val classReadResult = classPath.readClass(name)
        println("readClass name $name result ${classReadResult.isSuccess}")
        if (!classReadResult.isSuccess) {
            throw ClassNotFoundException("Class not found $name")
        }
        return classReadResult
    }

    private fun defClass(data: ByteArray): YuClass {
        println("defClass begin")
        val yuClass = parseClass(data)
        yuClass.loader = this
        resolveSuperCLass(yuClass)
        resolveInterfaces(yuClass)
        classMap[yuClass.name] = yuClass
        println("defClass over")
        return yuClass
    }

    private fun parseClass(data: ByteArray): YuClass {
        println("parseClass begin")
        val classFile = ClassFile()
        classFile.parse(data)
        println("parseClass over")
        return YuClass(classFile)
    }

    private fun resolveSuperCLass(yuClass: YuClass) {
        if (yuClass.name != "java/lang/Object") {
            yuClass.superClass = loadClass(yuClass.superClassName)
        }
    }

    private fun resolveInterfaces(yuClass: YuClass) {
        for (interfaceName in yuClass.interfaceNames) {
            val yuInterface = loadClass(interfaceName)
            yuClass.interfaces.add(yuInterface)
        }
    }

    private fun link(yuClass: YuClass) {
        verify(yuClass)
        prepare(yuClass)
    }

    // todo verify the class
    private fun verify(yuClass: YuClass) {

    }

    private fun prepare(yuClass: YuClass) {
        calcInstanceFieldSlotIds(yuClass)
        calcStaticFieldSlotIds(yuClass)
        allocAndInitStaticVars(yuClass)
    }

    private fun calcInstanceFieldSlotIds(yuClass: YuClass) {
        var slotId = 0
        if (yuClass.superClass != null) {
            slotId = yuClass.superClass!!.instanceSlotCount
        }
        for (field in yuClass.fields) {
            if (!field.isStatic()) {
                field.slotId = slotId
                slotId++
                if (field.isLongOrDouble()) {
                    slotId++
                }
            }
        }
        yuClass.instanceSlotCount = slotId
    }

    private fun calcStaticFieldSlotIds(yuClass: YuClass) {
        var slotId = 0
        for (field in yuClass.fields) {
            if (field.isStatic()) {
                field.slotId = slotId
                slotId++
                if (field.isLongOrDouble()) {
                    slotId++
                }
            }
        }
        yuClass.staticSlotCount = slotId
        yuClass.staticVariables = LocalVariable(slotId)
    }

    private fun allocAndInitStaticVars(yuClass: YuClass) {
        for (field in yuClass.fields) {
            if (field.isStatic() and field.isFinal()) {
                initStaticFinalVariables(yuClass, field)
            }
        }
    }

    private fun initStaticFinalVariables(yuClass: YuClass, field: YuField) {
        val variables = yuClass.staticVariables
        val constantPool = yuClass.constantPool
        val cpIndex = field.constValueIndex
        val slotId = field.slotId
        if (cpIndex <= 0) {
            return
        }
        when (field.descriptor) {
            "Z", "B", "C", "S", "I" -> {
                val value = constantPool.getConstant(cpIndex) as IntegerInfo
                variables.setInt(slotId, value.iValue)
            }
            "J" -> {
                val value = constantPool.getConstant(cpIndex) as Long
                variables.setLong(slotId, value)
            }
            "F" -> {
                val value = constantPool.getConstant(cpIndex) as Float
                variables.setFloat(slotId, value)
            }
            "D" -> {
                val value = constantPool.getConstant(cpIndex) as Double
                variables.setDouble(slotId, value)
            }
            "Ljava/lang/String;" -> {
                val str = constantPool.getConstant(cpIndex) as String
                val jStr = InternedString.jString(yuClass.loader, str)
                variables.setRef(slotId, jStr)
            }
        }
    }
}
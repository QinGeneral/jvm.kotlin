package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.ClassFile
import com.elements.jvmbykotlin.classpath.ClassReadResult
import com.elements.jvmbykotlin.classpath.Classpath
import com.elements.jvmbykotlin.runtimedata.LocalVariable

class YuClassLoader(
    val classPath: Classpath
) {
    val classMap = HashMap<String, YuClass>()

    fun loadClass(name: String): YuClass {
        if (name in classMap.keys) {
            return classMap[name]!!
        }
        return loadNonArrayClass(name)
    }

    fun loadNonArrayClass(name: String): YuClass {
        val classReadResult = readClass(name)
        val yuClass = defClass(classReadResult.byteCode!!)
        link(yuClass)
        println("loadNonArrayClass $name")
        return yuClass
    }

    fun readClass(name: String): ClassReadResult {
        println("readClass name $name")
        val classReadResult = classPath.readClass(name)
        println("readClass name $name result ${classReadResult.isSuccess}")
        if (!classReadResult.isSuccess) {
            throw ClassNotFoundException("Class not found $name")
        }
        return classReadResult
    }

    fun defClass(data: ByteArray): YuClass {
        println("defClass begin")
        val yuClass = parseClass(data)
        yuClass.loader = this
        resolveSuperCLass(yuClass)
        resolveInterfaces(yuClass)
        classMap[yuClass.name] = yuClass
        println("defClass over")
        return yuClass
    }

    fun parseClass(data: ByteArray): YuClass {
        println("parseClass begin")
        val classFile = ClassFile()
        classFile.parse(data)
        println("parseClass over")
        return YuClass(classFile)
    }

    fun resolveSuperCLass(yuClass: YuClass) {
        if (yuClass.name != "java/lang/Object") {
            yuClass.superClass = loadClass(yuClass.superClassName)
        }
    }

    fun resolveInterfaces(yuClass: YuClass) {
        for (interfaceName in yuClass.interfaceNames) {
            val yuInterface = loadClass(interfaceName)
            yuClass.interfaces.add(yuInterface)
        }
    }

    fun link(yuClass: YuClass) {
        verify(yuClass)
        prepare(yuClass)
    }

    // todo verify the class
    fun verify(yuClass: YuClass) {

    }

    fun prepare(yuClass: YuClass) {
        calcInstanceFieldSlotIds(yuClass)
        calcStaticFieldSlotIds(yuClass)
    }

    fun calcInstanceFieldSlotIds(yuClass: YuClass) {
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

    fun calcStaticFieldSlotIds(yuClass: YuClass) {
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

    fun allocAndInitStaticVars(yuClass: YuClass) {
        for (field in yuClass.fields) {
            if (field.isStatic() and field.isFinal()) {
                initStaticFinalVariables(yuClass, field)
            }
        }
    }

    fun initStaticFinalVariables(yuClass: YuClass, field: YuField) {
        val variables = yuClass.staticVariables
        val constantPool = yuClass.constantPool
        val cpIndex = field.constValueIndex
        val slotId = field.slotId
        if (cpIndex <= 0) {
            return
        }
        when (field.descriptor) {
            "Z", "B", "C", "S", "I" -> {
                val value = constantPool.getConstant(cpIndex) as Int
                variables.setInt(slotId, value)
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
                // todo
                throw UnsupportedOperationException("String not support")
            }
        }
    }
}
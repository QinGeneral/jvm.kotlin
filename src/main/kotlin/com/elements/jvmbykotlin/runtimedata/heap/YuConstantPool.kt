package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.entity.constantpool.*
import com.elements.jvmbykotlin.runtimedata.heap.ref.FieldRef
import com.elements.jvmbykotlin.runtimedata.heap.ref.InterfaceMethodRef
import com.elements.jvmbykotlin.runtimedata.heap.ref.MethodRef

class YuConstantPool(val yuClass: YuClass, constantPool: ConstantPool) {
    val constants: MutableMap<Int, Any> = HashMap()

    init {
        for (key in constantPool.items.keys) {
            if (key == 41) {
                println()
            }
            val cpInfo = constantPool.items[key]
            when (cpInfo) {
                is IntegerInfo ->
                    constants[key] = cpInfo
                is FloatInfo ->
                    constants[key] = cpInfo.fValue
                is DoubleInfo ->
                    constants[key] = cpInfo.dValue
                is LongInfo ->
                    constants[key] = cpInfo.lValue
                is StringInfo ->
                    constants[key] = cpInfo.getValue(constantPool)
                is ClassInfo -> {
                    val className = cpInfo.getValue(constantPool)
                    constants[key] = ClassRef(this, className, null)
                }
                is FieldRefInfo -> {
                    val className = cpInfo.getClassName(constantPool)
                    val nameAndTypeInfo = cpInfo.getNameAndType(constantPool)
                    constants[key] =
                        FieldRef(
                            this,
                            className,
                            null,
                            nameAndTypeInfo.getName(constantPool),
                            nameAndTypeInfo.getDescriptor(constantPool),
                        )
                }
                is InterfaceMethodRefInfo -> {
                    val className = cpInfo.getClassName(constantPool)
                    val nameAndTypeInfo = cpInfo.getNameAndType(constantPool)
                    constants[key] =
                        InterfaceMethodRef(
                            this,
                            className,
                            null,
                            nameAndTypeInfo.getName(constantPool),
                            nameAndTypeInfo.getDescriptor(constantPool),
                        )
                }
                is MethodRefInfo -> {
                    val className = cpInfo.getClassName(constantPool)
                    val nameAndTypeInfo = cpInfo.getNameAndType(constantPool)
                    constants[key] =
                        MethodRef(
                            this,
                            className,
                            null,
                            nameAndTypeInfo.getName(constantPool),
                            nameAndTypeInfo.getDescriptor(constantPool),
                        )
                }
                is Utf8Info -> {

//                    constants[key] =
                }
            }

        }
    }

    fun getConstant(index: Int): Any {
        if (index in constants.keys) {
            return constants[index]!!
        }
        throw IndexOutOfBoundsException("$index not in constants")
    }
}
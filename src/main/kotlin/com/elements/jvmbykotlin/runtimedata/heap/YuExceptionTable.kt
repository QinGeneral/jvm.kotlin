package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.entity.attribute.CodeAttribute

/**
 * Java exception table for exception handling
 *
 * @author hanzhang
 */
class YuExceptionTable(val exceptionTable: ArrayList<CodeAttribute.ExceptionTableItem>, constantPool: YuConstantPool) {
    val exceptionHandlers = ArrayList<ExceptionHandler>()

    init {
        for (tableItem in exceptionTable) {
            exceptionHandlers.add(
                ExceptionHandler(
                    tableItem.startPC.toInt(),
                    tableItem.endPC.toInt(),
                    tableItem.handlerPC.toInt(),
                    getCatchType(tableItem.catchType.toInt(), constantPool)
                )
            )
        }
    }

    fun findExceptionHandler(exceptionClass: YuClass, pc: Int): ExceptionHandler? {
        for (handler in exceptionHandlers) {
            if (pc >= handler.startPC && pc < handler.endPC) {
                if (handler.catchType == null) {
                    return handler
                }
                val catchClass = handler.catchType.resolvedClass()
                if (catchClass == exceptionClass || catchClass.isSuperClassOf(exceptionClass)) {
                    return handler
                }
            }
        }
        return null
    }

    private fun getCatchType(index: Int, constantPool: YuConstantPool): ClassRef? {
        if (index == 0) {
            return null
        }
        return constantPool.getConstant(index) as ClassRef
    }

    class ExceptionHandler(
        val startPC: Int,
        val endPC: Int,
        val handlerPC: Int,
        val catchType: ClassRef?
    ) {
    }
}
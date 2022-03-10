package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.YuClass
import com.elements.jvmbykotlin.runtimedata.heap.YuClassLoader
import java.lang.Exception

/**
 * Create base type array object
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.newarray">newarray</a>
 *
 * @author hanzhang
 */
class NewArray : Instruction {
    enum class ArrayType(val value: Int) {
        BOOLEAN(4),
        CHAR(5),
        FLOAT(6),
        DOUBLE(7),
        BYTE(8),
        SHORT(9),
        INT(10),
        LONG(11);
    }

    var aType: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        aType = bytecodeReader.readInt8()
    }

    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val count = stack.popInt()
        if (count < 0) {
            throw NegativeArraySizeException("Array index is lower then zero $count")
        }
        val classLoader = frame.method.yuClass.loader
        val arrayClass = getPrimitiveArray(classLoader, aType)
        val array = ArrayObject.of(arrayClass, count)
        stack.pushRef(array)
    }

    private fun getPrimitiveArray(yuClassLoader: YuClassLoader, aType: Int): YuClass {
        when (aType) {
            ArrayType.BOOLEAN.value ->
                return yuClassLoader.loadClass("[Z")
            ArrayType.BYTE.value ->
                return yuClassLoader.loadClass("[B")
            ArrayType.CHAR.value ->
                return yuClassLoader.loadClass("[C")
            ArrayType.SHORT.value ->
                return yuClassLoader.loadClass("[S")
            ArrayType.INT.value ->
                return yuClassLoader.loadClass("[I")
            ArrayType.LONG.value ->
                return yuClassLoader.loadClass("[J")
            ArrayType.FLOAT.value ->
                return yuClassLoader.loadClass("[F")
            ArrayType.DOUBLE.value ->
                return yuClassLoader.loadClass("[D")
            else ->
                throw Exception("Invalid type $aType")
        }

    }
}
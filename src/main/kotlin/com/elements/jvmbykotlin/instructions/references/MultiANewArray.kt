package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.OperandStack
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.ClassRef
import com.elements.jvmbykotlin.runtimedata.heap.YuClass

class MultiANewArray : Instruction {
    var index: Int = 0
    var dimensions: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        index = bytecodeReader.readUInt16()
        dimensions = bytecodeReader.readInt8()
    }

    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val classRef = constantPool.getConstant(index) as ClassRef
        val arrayClass = classRef.resolvedClass()
        val stack = frame.operandStack
        val counts = popAndCheckCounts(stack, dimensions)
        val array = createMultiDimensionalArray(0, counts, arrayClass)
        stack.pushRef(array)
    }

    private fun popAndCheckCounts(stack: OperandStack, dimensions: Int): IntArray {
        val counts = IntArray(dimensions)
        for (i in (dimensions - 1) downTo 0) {
            counts[i] = stack.popInt()
            if (counts[i] < 0) {
                throw NegativeArraySizeException()
            }
        }
        return counts
    }

    private fun createMultiDimensionalArray(countsIndex: Int, counts: IntArray, arrayClass: YuClass): ArrayObject {
        val count = counts[countsIndex]
        val array = ArrayObject.of(arrayClass, count)
        if ((counts.size - countsIndex) > 1) {
            val refs = array.refs()
            for (i in refs.indices) {
                refs[i] = createMultiDimensionalArray(countsIndex + 1, counts, arrayClass.getComponentClass())
            }
        }
        return array
    }
}
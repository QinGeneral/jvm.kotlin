package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.YuObject

/**
 * Load array to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.aaload">aaload</a>
 *
 * @author hanzhang
 */
object ArrayLoad {
    fun checkNotNull(arrayRef: YuObject?) {
        if (arrayRef == null) {
            throw NullPointerException()
        }
    }

    fun checkIndex(arrayLength: Int, index: Int) {
        println("checkIndex $arrayLength, $index")
        if ((index < 0) or (index >= arrayLength)) {
            throw ArrayIndexOutOfBoundsException()
        }
    }
}

class AALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val refs = (arrayRef as ArrayObject).refs()
        ArrayLoad.checkIndex(refs.size, index)
        stack.pushRef(refs[index])
    }
}

class BALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val bytes = (arrayRef as ArrayObject).bytes()
        ArrayLoad.checkIndex(bytes.size, index)
        stack.pushInt(bytes[index].toInt())
    }
}

class CALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val chars = (arrayRef as ArrayObject).chars()
        ArrayLoad.checkIndex(chars.size, index)
        stack.pushInt(chars[index].code)
    }
}

class DALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val doubles = (arrayRef as ArrayObject).doubles()
        ArrayLoad.checkIndex(doubles.size, index)
        stack.pushDouble(doubles[index])
    }
}

class FALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val floats = (arrayRef as ArrayObject).floats()
        ArrayLoad.checkIndex(floats.size, index)
        stack.pushFloat(floats[index])
    }
}

class IALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val ints = (arrayRef as ArrayObject).ints()
        ArrayLoad.checkIndex(ints.size, index)
        stack.pushInt(ints[index])
    }
}

class SALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val shorts = (arrayRef as ArrayObject).shorts()
        ArrayLoad.checkIndex(shorts.size, index)
        stack.pushInt(shorts[index].toInt())
    }
}

class LALoad : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val longs = (arrayRef as ArrayObject).longs()
        ArrayLoad.checkIndex(longs.size, index)
        stack.pushLong(longs[index])
    }
}
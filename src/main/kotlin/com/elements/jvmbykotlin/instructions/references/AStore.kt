package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject

class AAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popRef()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val refs = (arrayRef as ArrayObject).refs()
        ArrayLoad.checkIndex(refs.size, index)
        refs[index] = value
    }
}

class BAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popInt()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val bytes = (arrayRef as ArrayObject).bytes()
        ArrayLoad.checkIndex(bytes.size, index)
        bytes[index] = value.toByte()
    }
}

class CAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popInt()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val chars = (arrayRef as ArrayObject).chars()
        ArrayLoad.checkIndex(chars.size, index)
        chars[index] = value.toChar()
    }
}

class DAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popDouble()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val doubles = (arrayRef as ArrayObject).doubles()
        ArrayLoad.checkIndex(doubles.size, index)
        doubles[index] = value
    }
}

class FAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popFloat()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val floats = (arrayRef as ArrayObject).floats()
        ArrayLoad.checkIndex(floats.size, index)
        floats[index] = value
    }
}

class IAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popInt()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val ints = (arrayRef as ArrayObject).ints()
        ArrayLoad.checkIndex(ints.size, index)
        ints[index] = value
    }
}

class LAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popLong()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val longs = (arrayRef as ArrayObject).longs()
        ArrayLoad.checkIndex(longs.size, index)
        longs[index] = value
    }
}

class SAStore : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val value = stack.popInt()
        val index = stack.popInt()
        val arrayRef = stack.popRef()
        ArrayLoad.checkNotNull(arrayRef)
        val shorts = (arrayRef as ArrayObject).shorts()
        ArrayLoad.checkIndex(shorts.size, index)
        shorts[index] = value.toShort()
    }
}
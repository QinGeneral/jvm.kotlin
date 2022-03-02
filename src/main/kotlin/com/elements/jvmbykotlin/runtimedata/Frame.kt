package com.elements.jvmbykotlin.runtimedata

import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

class Frame(val thread: YuThread, val method: YuMethod) {
    var lowerFrame: Frame? = null
    val localVariable: LocalVariable = LocalVariable(method.maxLocals)
    val operandStack: OperandStack = OperandStack(method.maxStack)
    var nextPC: Int = 0

    fun revertNextPC() {
        nextPC = thread.pc
    }

    override fun toString(): String {
        return "Frame(localVariable=$localVariable,\n operandStack=$operandStack,)"
    }
}
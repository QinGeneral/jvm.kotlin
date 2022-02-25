package com.elements.jvmbykotlin.runtimedata

class Frame(val thread: YuThread, val maxLocals: Int, val maxStack: Int) {
    var lowerFrame: Frame? = null
    val localVariable: LocalVariable = LocalVariable(maxLocals)
    val operandStack: OperandStack = OperandStack(maxStack)
    var nextPC: Int = 0
    override fun toString(): String {
        return "Frame(localVariable=$localVariable,\n operandStack=$operandStack,)"
    }
}
package com.elements.jvmbykotlin.runtimedata

class Frame(val maxLocals: Int, val maxStack: Int) {
    var lowerFrame: Frame? = null
    val localVariable: LocalVariable = LocalVariable(maxLocals)
    val operandStack: OperandStack = OperandStack(maxStack)


}
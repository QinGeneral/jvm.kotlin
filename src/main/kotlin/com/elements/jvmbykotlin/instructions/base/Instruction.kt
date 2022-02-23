package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

interface Instruction {
    fun fetchOperands(bytecodeReader: BytecodeReader)
    fun execute(frame: Frame)
}
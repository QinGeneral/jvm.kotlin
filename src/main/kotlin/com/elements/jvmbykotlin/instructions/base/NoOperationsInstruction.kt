package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

open class NoOperationsInstruction : Instruction {
    override fun fetchOperands(bytecodeReader: BytecodeReader) {

    }

    override fun execute(frame: Frame) {

    }
}
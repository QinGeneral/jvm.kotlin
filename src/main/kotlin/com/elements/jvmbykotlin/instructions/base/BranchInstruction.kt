package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

open class BranchInstruction : Instruction {
    var offset: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        offset = bytecodeReader.readInt16()
    }

    override fun execute(frame: Frame) {
    }
}
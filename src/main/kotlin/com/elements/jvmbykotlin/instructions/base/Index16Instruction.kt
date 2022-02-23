package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

class Index16Instruction : Instruction {
    var index: UInt = 0u
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        index = bytecodeReader.readUInt16()
    }

    override fun execute(frame: Frame) {
        TODO("Not yet implemented")
    }
}
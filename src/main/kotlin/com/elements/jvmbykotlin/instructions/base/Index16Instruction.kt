package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Base instruction which read 2 byte (16 bit)
 *
 * @author hanzhang
 */
open class Index16Instruction : Instruction {
    var index: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        index = bytecodeReader.readUInt162()
    }

    override fun execute(frame: Frame) {

    }
}
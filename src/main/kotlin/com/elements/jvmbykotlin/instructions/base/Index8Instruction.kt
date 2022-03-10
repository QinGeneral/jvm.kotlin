package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Base instruction which read 1 byte (8 bit)
 *
 * @author hanzhang
 */
open class Index8Instruction : Instruction {
    var index: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        index = bytecodeReader.readInt8()
    }

    override fun execute(frame: Frame) {

    }
}
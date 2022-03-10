package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Base instruction for nothing to do
 *
 * @author hanzhang
 */
open class NoOperationsInstruction : Instruction {
    override fun fetchOperands(bytecodeReader: BytecodeReader) {

    }

    override fun execute(frame: Frame) {

    }
}
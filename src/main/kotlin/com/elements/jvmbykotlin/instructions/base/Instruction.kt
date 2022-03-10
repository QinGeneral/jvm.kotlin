package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Instruction interface
 *
 * @author hanzhang
 */
interface Instruction {
    /**
     * read instruction byte
     *
     * @param bytecodeReader Code byte reader
     */
    fun fetchOperands(bytecodeReader: BytecodeReader)

    /**
     * execute instruction
     *
     * @param frame current method's frame
     */
    fun execute(frame: Frame)
}
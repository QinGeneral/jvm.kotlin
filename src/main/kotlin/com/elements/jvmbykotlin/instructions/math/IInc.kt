package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * add value to int
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.iinc">iinc</a>
 *
 * @author hanzhang
 */
class IInc : NoOperationsInstruction() {
    var index: Int = 0
    var const: Int = 0

    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        index = bytecodeReader.readInt8()
        const = bytecodeReader.readByte().toInt()
    }

    override fun execute(frame: Frame) {
        val localVariable = frame.localVariable
        var value = localVariable.getInt(index)
        value += const
        localVariable.setInt(index, value)
    }
}
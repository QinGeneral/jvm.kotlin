package com.elements.jvmbykotlin.instructions.math

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class IInc : NoOperationsInstruction() {
    var index: Int = 0
    var const: Int = 0

    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        index = bytecodeReader.readInt8()
        const = bytecodeReader.readInt8()
    }

    override fun execute(frame: Frame) {
        val localVariable = frame.localVariable
        var value = localVariable.getInt(index)
        value += const
        localVariable.setInt(index, value)
    }
}
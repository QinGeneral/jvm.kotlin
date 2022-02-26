package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class BiPush : Instruction {
    var value: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        value = bytecodeReader.readInt8()
    }

    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(value)
    }
}
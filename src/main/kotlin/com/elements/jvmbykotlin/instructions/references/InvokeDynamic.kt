package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class InvokeDynamic : Instruction {
    var bootstrapMethodAttrIndex: Int = 0
    var nameAndTypeIndex: Int = 0

    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        bootstrapMethodAttrIndex = bytecodeReader.readInt16()
        nameAndTypeIndex = bytecodeReader.readInt16()
    }

    override fun execute(frame: Frame) {
        // todo
        println("InvokeDynamic execute")
    }
}
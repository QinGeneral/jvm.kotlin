package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Invoke dynamic method
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.invokedynamic">invokedynamic</a>
 *
 * @author hanzhang
 */
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
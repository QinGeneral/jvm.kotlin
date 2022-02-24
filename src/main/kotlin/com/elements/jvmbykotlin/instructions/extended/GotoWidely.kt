package com.elements.jvmbykotlin.instructions.extended

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.control.Goto

class GotoWidely : Goto() {
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        offset = bytecodeReader.readInt32()
    }
}
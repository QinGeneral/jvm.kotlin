package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class InvokeSpecial : Index16Instruction() {
    override fun execute(frame: Frame) {
        // todo
        frame.operandStack.popRef()
    }
}
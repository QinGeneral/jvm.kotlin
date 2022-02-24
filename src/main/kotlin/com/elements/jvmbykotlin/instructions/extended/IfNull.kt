package com.elements.jvmbykotlin.instructions.extended

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

class IfNull : BranchInstruction() {
    override fun execute(frame: Frame) {
        val ref = frame.operandStack.popRef()
        if (ref == null) {
            BranchLogic.branch(frame, offset)
        }
    }
}
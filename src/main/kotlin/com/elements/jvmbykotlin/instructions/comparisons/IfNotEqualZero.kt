package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

class IfNotEqualZero : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack.popInt()
        if (value != 0) {
            BranchLogic.branch(frame, offset)
        }
    }
}
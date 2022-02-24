package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

class IfICmpLT : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popInt()
        val value1 = frame.operandStack.popInt()
        if (value1 < value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}
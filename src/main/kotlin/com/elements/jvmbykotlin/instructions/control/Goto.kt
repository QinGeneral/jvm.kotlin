package com.elements.jvmbykotlin.instructions.control

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

open class Goto : BranchInstruction() {
    override fun execute(frame: Frame) {
        BranchLogic.branch(frame, offset)
    }
}
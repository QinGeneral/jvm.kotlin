package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class DCmpL : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        DCmp.dcmp(frame, false)
    }
}
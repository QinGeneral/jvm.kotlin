package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class FCmpG : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FCmp.fcmp(frame, true)
    }
}
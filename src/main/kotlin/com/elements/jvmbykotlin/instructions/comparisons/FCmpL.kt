package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class FCmpL : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        FCmp.fcmp(frame, false)
    }
}
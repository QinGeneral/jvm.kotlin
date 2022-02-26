package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LDC_W : Index8Instruction() {
    override fun execute(frame: Frame) {
        LDC.ldc(frame, index)
    }
}
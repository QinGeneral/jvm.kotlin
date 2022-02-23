package com.elements.jvmbykotlin.instructions.loads

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class ILoad0 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        ILoad.iLoad(frame, 0)
    }
}
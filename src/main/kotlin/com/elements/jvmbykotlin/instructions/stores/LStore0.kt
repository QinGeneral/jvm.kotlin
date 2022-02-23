package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LStore0 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        LStore.lStore(frame, 0)
    }
}
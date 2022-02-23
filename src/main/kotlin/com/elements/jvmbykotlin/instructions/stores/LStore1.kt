package com.elements.jvmbykotlin.instructions.stores

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LStore1 : NoOperationsInstruction() {

    override fun execute(frame: Frame) {
        LStore.lStore(frame, 1)
    }
}
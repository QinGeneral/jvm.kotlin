package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Swap two item on top of operand stack
 *
 * @author hanzhang
 * @since 2022-02-24
 */
class Swap : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val slot1 = stack.popSlot()
        val slot2 = stack.popSlot()
        stack.pushSlot(slot1)
        stack.pushSlot(slot2)
    }
}
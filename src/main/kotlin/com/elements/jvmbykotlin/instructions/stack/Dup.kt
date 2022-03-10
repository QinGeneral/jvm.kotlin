package com.elements.jvmbykotlin.instructions.stack

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Duplicate the slot on top of operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.dup">dup</a>
 *
 * @author hanzhang
 */
class Dup : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot)
        frame.operandStack.pushSlot(slot)
    }
}

class Dup2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot1 = frame.operandStack.popSlot()
        val slot2 = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
    }
}

class Dup2X1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot1 = frame.operandStack.popSlot()
        val slot2 = frame.operandStack.popSlot()
        val slot3 = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
        frame.operandStack.pushSlot(slot3)
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
    }
}

class Dup2X2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot1 = frame.operandStack.popSlot()
        val slot2 = frame.operandStack.popSlot()
        val slot3 = frame.operandStack.popSlot()
        val slot4 = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
        frame.operandStack.pushSlot(slot4)
        frame.operandStack.pushSlot(slot3)
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
    }
}

class DupX1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot1 = frame.operandStack.popSlot()
        val slot2 = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot1)
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
    }
}

class DupX2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val slot1 = frame.operandStack.popSlot()
        val slot2 = frame.operandStack.popSlot()
        val slot3 = frame.operandStack.popSlot()
        frame.operandStack.pushSlot(slot1)
        frame.operandStack.pushSlot(slot3)
        frame.operandStack.pushSlot(slot2)
        frame.operandStack.pushSlot(slot1)
    }
}
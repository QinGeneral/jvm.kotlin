package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Push int 0/1/2/3/4/5/-1 to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iconst_i">iconst_i</a>
 *
 * @author hanzhang
 */
class IConst0 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(0)
    }
}

class IConst1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(1)
    }
}

class IConst2 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(2)
    }
}

class IConst3 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(3)
    }
}

class IConst4 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(4)
    }
}

class IConst5 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(5)
    }
}

class IConstM1 : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(-1)
    }
}
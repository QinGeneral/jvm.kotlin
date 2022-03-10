package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Push byte value in code byte to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.bipush">bipush</a>
 *
 * @author hanzhang
 */
class BiPush : Instruction {
    var value: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        value = bytecodeReader.readInt8()
    }

    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(value)
    }
}

/**
 * Push short value in code byte to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.sipush">sipush</a>
 *
 * @author hanzhang
 */
class SiPush : Instruction {
    var value: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        value = bytecodeReader.readInt16()
    }

    override fun execute(frame: Frame) {
        frame.operandStack.pushInt(value)
    }
}
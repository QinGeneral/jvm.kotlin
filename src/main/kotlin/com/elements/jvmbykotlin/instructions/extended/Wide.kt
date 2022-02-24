package com.elements.jvmbykotlin.instructions.extended

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.instructions.loads.ILoad
import com.elements.jvmbykotlin.instructions.math.IInc
import com.elements.jvmbykotlin.runtimedata.Frame
import java.lang.UnsupportedOperationException

class Wide : Instruction {
    lateinit var modifiedInstruction: Instruction

    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        val opcode = bytecodeReader.readUInt8().toInt()
        when (opcode) {
            // iload
            0x15 -> {
                val instruction = ILoad()
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // lload
            0x16 -> {
                //todo
            }
            // fload
            0x17 -> {

            }
            // dload
            0x18 -> {

            }
            // aload
            0x19 -> {

            }
            // istore
            0x36 -> {

            }
            // lstore
            0x37 -> {

            }
            // fstore
            0x38 -> {

            }
            // dstore
            0x39 -> {

            }
            // astore
            0x3a -> {

            }
            // iinc
            0x84 -> {
                val instruction = IInc()
                instruction.index = bytecodeReader.readInt16()
                instruction.const = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // ret
            0xa9 -> {
                throw UnsupportedOperationException("Unsupported opcode: 0xa9")
            }
        }
    }

    override fun execute(frame: Frame) {
        modifiedInstruction.execute(frame)
    }
}
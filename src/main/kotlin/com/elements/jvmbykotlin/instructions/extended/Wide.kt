package com.elements.jvmbykotlin.instructions.extended

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.instructions.base.InstructionFactory
import com.elements.jvmbykotlin.instructions.loads.*
import com.elements.jvmbykotlin.instructions.math.IInc
import com.elements.jvmbykotlin.instructions.stores.*
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Extend instruction to wide
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.wide">wide</a>
 *
 * @author hanzhang
 */
class Wide : Instruction {
    lateinit var modifiedInstruction: Instruction

    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        val opcode = bytecodeReader.readUInt8().toInt()
        when (opcode) {
            // iload
            0x15 -> {
                val instruction = InstructionFactory.create(opcode) as ILoad
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // lload
            0x16 -> {
                val instruction = InstructionFactory.create(opcode) as LLoad
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // fload
            0x17 -> {
                val instruction = InstructionFactory.create(opcode) as FLoad
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // dload
            0x18 -> {
                val instruction = InstructionFactory.create(opcode) as DLoad
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // aload
            0x19 -> {
                val instruction = InstructionFactory.create(opcode) as ALoad
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // istore
            0x36 -> {
                val instruction = InstructionFactory.create(opcode) as IStore
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // lstore
            0x37 -> {
                val instruction = InstructionFactory.create(opcode) as LStore
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // fstore
            0x38 -> {
                val instruction = InstructionFactory.create(opcode) as FStore
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // dstore
            0x39 -> {
                val instruction = InstructionFactory.create(opcode) as DStore
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // astore
            0x3a -> {
                val instruction = InstructionFactory.create(opcode) as AStore
                instruction.index = bytecodeReader.readInt16()
                modifiedInstruction = instruction
            }
            // iinc
            0x84 -> {
                val instruction = InstructionFactory.create(opcode) as IInc
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
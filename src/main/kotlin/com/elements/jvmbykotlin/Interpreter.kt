package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.classfile.entity.MethodInfo
import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.InstructionFactory
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread

class Interpreter {
    fun interpret(methodInfo: MethodInfo) {
        val codeAttribute = methodInfo.getCodeAttribute()!!
        val maxLocals = codeAttribute.maxLocals.toInt()
        val maxStack = codeAttribute.maxStack.toInt()
        val bytecode = codeAttribute.code

        val thread = YuThread()
        val frame = Frame(thread, maxLocals, maxStack)
        thread.pushFrame(frame)
        try {
            loop(thread, bytecode)
        } catch (e: Exception) {
            e.printStackTrace()
            println("loop exception " + frame.localVariable + " " + frame.operandStack)
        }
    }

    fun loop(thread: YuThread, bytecode: ByteArray) {
        val frame = thread.popFrame()
        val reader = BytecodeReader()
        println("============================================")
        while (true) {
            val pc = frame.nextPC
            thread.pc = pc

            reader.reset(bytecode, pc)
            val opcode = reader.readInt8()
            val instruction = InstructionFactory.create(opcode)
            instruction.fetchOperands(reader)
            frame.nextPC = reader.pc
            instruction.execute(frame)
            println("Execute PC: $pc, Instruction: ${instruction.javaClass.simpleName}")
            println("--------------------------------------------")
            println("operandStack\n${frame.operandStack}")
            println("--------------------------------------------")
            println("localVariable\n${frame.localVariable}")
            println("============================================")
        }
    }
}
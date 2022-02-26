package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.InstructionFactory
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

class Interpreter {
    fun interpret(method: YuMethod) {
        println("interpret ${method.name}")
        val thread = YuThread()
        val frame = Frame(thread, method)
        thread.pushFrame(frame)
        try {
            loop(thread, method.code)
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
            println("Execute PC before: $pc")
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
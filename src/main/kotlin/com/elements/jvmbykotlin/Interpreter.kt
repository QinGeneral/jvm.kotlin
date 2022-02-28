package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.instructions.base.InstructionFactory
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

class Interpreter {
    fun interpret(method: YuMethod, isLogInstruction: Boolean) {
        println("interpret ${method.name}")
        val thread = YuThread()
        val frame = Frame(thread, method)
        thread.pushFrame(frame)
        try {
            loop(thread, isLogInstruction)
        } catch (e: Exception) {
            e.printStackTrace()
            logFrames(thread)
        }
    }

    fun loop(thread: YuThread, isLogInstruction: Boolean) {
        val reader = BytecodeReader()
        println("============================================")
        while (true) {
            val frame = thread.topFrame()
            val pc = frame.nextPC
            thread.pc = pc
            println("Execute PC before: $pc")
            reader.reset(frame.method.code, pc)
            val opcode = reader.readInt8()
            val instruction = InstructionFactory.create(opcode)
            instruction.fetchOperands(reader)
            frame.nextPC = reader.pc
            if (isLogInstruction) {
                logInstruction(frame, instruction)
            }
            instruction.execute(frame)
            println("Execute PC: $pc, Instruction: ${instruction.javaClass.simpleName}")
            println("--------------------------------------------")
            println("operandStack\n${frame.operandStack}")
            println("--------------------------------------------")
            println("localVariable\n${frame.localVariable}")
            println("============================================")
            if (thread.isStackEmpty()) {
                break
            }
        }
    }

    fun logFrames(thread: YuThread) {
        while (!thread.isStackEmpty()) {
            val frame = thread.popFrame()
            val method = frame.method
            val className = method.yuClass.name
            println(">> PC ${frame.nextPC} $className ${method.name} ${method.descriptor}")
        }
    }

    fun logInstruction(frame: Frame, instruction: Instruction) {
        val method = frame.method
        val className = method.yuClass.name
        println(">> PC $className ${method.name} ${frame.thread.pc} $instruction")
    }
}
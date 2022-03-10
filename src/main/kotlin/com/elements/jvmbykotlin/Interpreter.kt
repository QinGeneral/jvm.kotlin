package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.instructions.base.InstructionFactory
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.YuClassLoader
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

/**
 * Execute bytecode by interpret
 *
 * @author hanzhang
 */
class Interpreter {
    /**
     * execute the methods in thread
     *
     * @param mainThread thread
     * @param isLogInstruction if print the log to console
     */
    fun interpret(mainThread: YuThread, isLogInstruction: Boolean) {
        try {
            loop(mainThread, isLogInstruction)
        } catch (e: Exception) {
            e.printStackTrace()
            logFrames(mainThread)
        }
    }

    /**
     * loop to execute methods
     *
     * @param thread current thread
     * @param isLogInstruction if print the log
     */
    private fun loop(thread: YuThread, isLogInstruction: Boolean) {
        val reader = BytecodeReader()
        println("============================================")
        while (true) {
            val frame = thread.topFrame()
            val pc = frame.nextPC
            thread.pc = pc
            println("Execute PC before: $pc ${frame.method.yuClass.name} ${frame.method.name}")
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

    /**
     * print the frame info
     *
     * @param thread current thread
     */
    private fun logFrames(thread: YuThread) {
        while (!thread.isStackEmpty()) {
            val frame = thread.popFrame()
            val method = frame.method
            val className = method.yuClass.name
            println(">> PC ${frame.nextPC} $className ${method.name} ${method.descriptor}")
        }
    }

    /**
     * print the instruction info
     *
     * @param frame current executing method's frame
     * @param instruction current instruction in method
     */
    private fun logInstruction(frame: Frame, instruction: Instruction) {
        val method = frame.method
        val className = method.yuClass.name
        println(">> PC $className ${method.name} ${frame.thread.pc} $instruction")
    }
}
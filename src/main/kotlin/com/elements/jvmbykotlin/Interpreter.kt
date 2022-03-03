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

class Interpreter {
    fun interpret(method: YuMethod, isLogInstruction: Boolean, args: ArrayList<String>) {
        println("interpret ${method.name}")
        val thread = YuThread()
        val frame = Frame(thread, method)
        thread.pushFrame(frame)

        val jArgs = createArgsArray(method.yuClass.loader, args)
        frame.localVariable.setRef(0, jArgs)

        try {
            loop(thread, isLogInstruction)
        } catch (e: Exception) {
            e.printStackTrace()
            logFrames(thread)
        }
    }

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

    private fun createArgsArray(yuClassLoader: YuClassLoader, args: ArrayList<String>): ArrayObject {
        val stringClass = yuClassLoader.loadClass("java/lang/String")
        val argsArray = ArrayObject.of(stringClass.getArrayClass(), args.size)
        val jArgs = argsArray.refs()
        for (i in 0 until args.size) {
            jArgs[i] = InternedString.jString(yuClassLoader, args[i])
        }
        return argsArray
    }

    private fun logFrames(thread: YuThread) {
        while (!thread.isStackEmpty()) {
            val frame = thread.popFrame()
            val method = frame.method
            val className = method.yuClass.name
            println(">> PC ${frame.nextPC} $className ${method.name} ${method.descriptor}")
        }
    }

    private fun logInstruction(frame: Frame, instruction: Instruction) {
        val method = frame.method
        val className = method.yuClass.name
        println(">> PC $className ${method.name} ${frame.thread.pc} $instruction")
    }
}
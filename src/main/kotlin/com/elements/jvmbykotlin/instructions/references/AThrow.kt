package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.YuObject

class AThrow : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val exception = frame.operandStack.popRef() ?: throw NullPointerException()
        val thread = frame.thread
        if (!findAndGotoExceptionHandler(thread, exception)) {
            handleUncaughtException(thread, exception)
        }
    }

    private fun handleUncaughtException(thread: YuThread, exception: YuObject) {
        thread.clearStack()
        val javaMsg = exception.getRefVariable("detailMessage", "Ljava/lang/String;")
        val msg = InternedString.string(javaMsg!!)
        println("${exception.yuClass.javaName} : $msg")
        println("extra ${exception.extra}")
    }

    private fun findAndGotoExceptionHandler(thread: YuThread, exception: YuObject): Boolean {
        while (true) {
            val frame = thread.topFrame()
            val pc = frame.nextPC - 1
            val handlerPC = frame.method.findExceptionHandler(exception.yuClass, pc)
            if (handlerPC > 0) {
                val stack = frame.operandStack
                stack.clear()
                stack.pushRef(exception)
                frame.nextPC = handlerPC
                return true
            }
            thread.popFrame()
            if (thread.isStackEmpty()) {
                break
            }
        }
        return false
    }
}
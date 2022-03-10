package com.elements.jvmbykotlin.instructions.control

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Return value to last method:
 * 1. pop the value from current frame's operand stack
 * 2. set into last frame's local varible
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.return">return</a>
 *
 * @author hanzhang
 */
class Return : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        frame.thread.popFrame()
    }
}

class IReturn : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val currentFrame = frame.thread.popFrame()
        val invokerFrame = frame.thread.topFrame()
        val retVal = currentFrame.operandStack.popInt()
        invokerFrame.operandStack.pushInt(retVal)
    }
}

class FReturn : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val currentFrame = frame.thread.popFrame()
        val invokerFrame = frame.thread.topFrame()
        val retVal = currentFrame.operandStack.popFloat()
        invokerFrame.operandStack.pushFloat(retVal)
    }
}

class LReturn : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val currentFrame = frame.thread.popFrame()
        val invokerFrame = frame.thread.topFrame()
        val retVal = currentFrame.operandStack.popLong()
        invokerFrame.operandStack.pushLong(retVal)
    }
}

class DReturn : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val currentFrame = frame.thread.popFrame()
        val invokerFrame = frame.thread.topFrame()
        val retVal = currentFrame.operandStack.popDouble()
        invokerFrame.operandStack.pushDouble(retVal)
    }
}

class AReturn : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val currentFrame = frame.thread.popFrame()
        val invokerFrame = frame.thread.topFrame()
        val retVal = currentFrame.operandStack.popRef()
        invokerFrame.operandStack.pushRef(retVal)
    }
}
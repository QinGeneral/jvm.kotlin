package com.elements.jvmbykotlin.runtimedata

import java.util.*

/**
 * Java thread, which have pc and frame stack to execute code
 *
 * @author hanzhang
 */
class YuThread(
) {
    /**
     * program counter (register)
     * contains the address of the instruction being executed
     */
    var pc: Int = 0

    private val CAPACITY = 1024

    /**
     * A new frame is created/pushed each time a method is invoked.
     * A frame is destroyed/popped when its method invocation completes
     */
    val stack: Stack<Frame> = Stack()

    init {

    }

    fun isStackEmpty(): Boolean {
        return stack.isEmpty()
    }

    fun pushFrame(frame: Frame) {
        if (stack.size >= CAPACITY) {
            throw StackOverflowError("stack overflow")
        }
        if (stack.isNotEmpty()) {
            frame.lowerFrame = stack.peek()
        }
        stack.push(frame)
    }

    fun popFrame(): Frame {
        if (stack.isEmpty()) {
            throw UnsupportedOperationException("stack is empty")
        }
        val frame = stack.pop()
        if (stack.isNotEmpty()) {
            stack.peek().lowerFrame = null
        }
        return frame
    }

    fun topFrame(): Frame {
        if (stack.isEmpty()) {
            throw UnsupportedOperationException("stack is empty")
        }
        return stack.peek()
    }

    fun clearStack() {
        stack.clear()
    }
}
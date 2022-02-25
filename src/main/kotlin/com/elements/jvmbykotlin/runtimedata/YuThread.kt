package com.elements.jvmbykotlin.runtimedata

import java.util.*

class YuThread(
) {
    var pc: Int = 0
    private val CAPACITY = 1024
    val stack: Stack<Frame> = Stack()

    init {

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

    fun top(): Frame {
        if (stack.isEmpty()) {
            throw UnsupportedOperationException("stack is empty")
        }
        return stack.peek()
    }
}
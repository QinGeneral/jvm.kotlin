package com.elements.jvmbykotlin.runtimedata

import java.util.*

class YuThread(
    val pc: Int,
) {
    private val CAPACITY = 1024
    val stack: Stack<Frame> = Stack()

    init {

    }

    fun push(frame: Frame) {
        if (stack.size >= CAPACITY) {
            throw StackOverflowError("stack overflow")
        }
        if (stack.isNotEmpty()) {
            frame.lowerFrame = stack.peek()
        }
        stack.push(frame)
    }

    fun pop(): Frame {
        if (stack.isEmpty()) {
            throw UnsupportedOperationException("stack is empty")
        }
        val frame = stack.pop()
        stack.peek().lowerFrame = null
        return frame
    }

    fun top(): Frame {
        if (stack.isEmpty()) {
            throw UnsupportedOperationException("stack is empty")
        }
        return stack.peek()
    }
}
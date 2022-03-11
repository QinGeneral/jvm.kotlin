package com.elements.jvmbykotlin.runtimedata

import com.elements.jvmbykotlin.runtimedata.heap.YuMethod

/**
 * Frame in Java stack, each method has a frame, contain local variable, operand stack and so on
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.6">Frames</a>
 *
 * @author hanzhang
 */
class Frame(val thread: YuThread, val method: YuMethod) {
    var lowerFrame: Frame? = null

    /**
     * local variables, method params and maybe "this".
     */
    val localVariable: LocalVariable = LocalVariable(method.maxLocals)

    /**
     * Empty upon frame creation,
     * JVM instructions load value onto stack and
     * operates everything in the stack.
     *
     * e.g. [x = 32768] you would load constant 32768 onto stack,
     * then pop and store the value into local variable x.
     */
    val operandStack: OperandStack = OperandStack(method.maxStack)
    var nextPC: Int = 0

    fun revertNextPC() {
        nextPC = thread.pc
    }

    override fun toString(): String {
        return "Frame(localVariable=$localVariable,\n operandStack=$operandStack,)"
    }
}
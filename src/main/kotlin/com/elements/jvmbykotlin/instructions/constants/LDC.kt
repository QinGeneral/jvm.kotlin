package com.elements.jvmbykotlin.instructions.constants

import com.elements.jvmbykotlin.classfile.entity.constantpool.FloatInfo
import com.elements.jvmbykotlin.classfile.entity.constantpool.IntegerInfo
import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.instructions.base.Index8Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ClassRef
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.YuString

/**
 * Instruction for load constant.
 * Load from rum-time constant pool, then push to operand stack.
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se16/html/jvms-6.html#jvms-6.5.ldc">ldc</a>
 *
 * @author hanzhang
 */
class LDC : Index8Instruction() {
    companion object {
        fun ldc(frame: Frame, index: Int) {
            val stack = frame.operandStack
            val cla = frame.method.yuClass
            val constantPool = frame.method.yuClass.constantPool
            val c = constantPool.getConstant(index)
            println("LDC $c")
            when (c) {
                is Int ->
                    stack.pushInt(c)
                is IntegerInfo ->
                    stack.pushInt(c.iValue)
                is Float ->
                    stack.pushFloat(c)
                is FloatInfo ->
                    stack.pushFloat(c.fValue)
                is String -> {
                    val str = InternedString.jString(cla.loader, c)
                    stack.pushRef(str)
                }
                is ClassRef -> {
                    val classObject = c.resolvedClass().jClass
                    stack.pushRef(classObject)
                }
                else ->
                    throw UnsupportedOperationException("Unsupported $c")
            }
        }
    }

    override fun execute(frame: Frame) {
        ldc(frame, index)
    }
}

/**
 * Instruction for load wide variable to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se16/html/jvms-6.html#jvms-6.5.ldc_w">ldc_w</a>
 *
 * @author hanzhang
 */
class LDC_W : Index8Instruction() {
    override fun execute(frame: Frame) {
        LDC.ldc(frame, index)
    }
}

/**
 * Instruction for load 2 words variable, long or double to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se16/html/jvms-6.html#jvms-6.5.ldc2_w">ldc2_w</a>
 *
 * @author hanzhang
 */
class LDC2_W : Index16Instruction() {
    override fun execute(frame: Frame) {
        val stack = frame.operandStack
        val constantPool = frame.method.yuClass.constantPool
        val c = constantPool.getConstant(index)
        when (c) {
            is Long ->
                stack.pushLong(c)
            is Double ->
                stack.pushDouble(c)
            else ->
                throw UnsupportedOperationException("Unsupported $c")
        }
    }
}
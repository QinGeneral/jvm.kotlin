package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.ClassInitLogic
import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ref.FieldRef

/**
 * Get static field in class constant pool, push to operand stack
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.getstatic">getstatic</a>
 *
 * @author hanzhang
 */
class GetStatic : Index16Instruction() {
    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val fieldRef = constantPool.getConstant(index) as FieldRef
        val field = fieldRef.resolveField()
        val c = field.yuClass
        if (!c.isInitStarted) {
            frame.revertNextPC()
            ClassInitLogic.initClass(frame.thread, c)
            return
        }
        if (!field.isStatic()) {
            throw IncompatibleClassChangeError("Incompatible class ${c.name} ${field.name}")
        }

        val descriptor = field.descriptor
        val slotId = field.slotId
        val variable = c.staticVariables
        val stack = frame.operandStack
        println("slotId $slotId $descriptor")
        when (descriptor[0]) {
            'Z', 'B', 'C', 'S', 'I' ->
                stack.pushInt(variable.getInt(slotId))
            'F' ->
                stack.pushFloat(variable.getFloat(slotId))
            'J' ->
                stack.pushLong(variable.getLong(slotId))
            'D' ->
                stack.pushDouble(variable.getDouble(slotId))
            'L', '[' ->
                stack.pushRef(variable.getRef(slotId))
        }
    }
}
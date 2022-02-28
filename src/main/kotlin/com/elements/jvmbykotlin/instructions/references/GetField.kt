package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ref.FieldRef

class GetField : Index16Instruction() {
    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val fieldRef = constantPool.getConstant(index) as FieldRef
        val field = fieldRef.resolveField()
        val c = field.yuClass
        if (field.isStatic()) {
            throw IncompatibleClassChangeError("Incompatible class ${c.name} ${field.name}")
        }

        val descriptor = field.descriptor
        val stack = frame.operandStack
        val ref = stack.popRef() ?: throw NullPointerException("ref is null")
        val variable = ref.fields
        val slotId = field.slotId
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
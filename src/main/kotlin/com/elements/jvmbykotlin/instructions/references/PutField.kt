package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.FieldRef

class PutField : Index16Instruction() {
    override fun execute(frame: Frame) {
        val curMethod = frame.method
        val curClass = curMethod.yuClass
        val constantPool = curClass.constantPool
        val fieldRef = constantPool.getConstant(index) as FieldRef
        val field = fieldRef.resolveField()
        val c = field.yuClass
        if (field.isStatic()) {
            throw IncompatibleClassChangeError("Incompatible class ${curClass.name} ${field.name}")
        }
        if (field.isFinal()) {
            if ((curClass != c) or (curMethod.name != "<init>")) {
                throw IllegalAccessError("Illegal access ${c.name} ${curMethod.name}")
            }
        }

        val descriptor = field.descriptor
        val slotId = field.slotId
        val stack = frame.operandStack
        when (descriptor[0]) {
            'Z', 'B', 'C', 'S', 'I' -> {
                val value = stack.popInt()
                val ref = stack.popRef() ?: throw NullPointerException("ref is null")
                ref.fields.setInt(slotId, value)
            }
            'F' -> {
                val value = stack.popFloat()
                val ref = stack.popRef() ?: throw NullPointerException("ref is null")
                ref.fields.setFloat(slotId, value)
            }
            'J' -> {
                val value = stack.popLong()
                val ref = stack.popRef() ?: throw NullPointerException("ref is null")
                ref.fields.setLong(slotId, value)
            }
            'D' -> {
                val value = stack.popDouble()
                val ref = stack.popRef() ?: throw NullPointerException("ref is null")
                ref.fields.setDouble(slotId, value)
            }
            'L', '[' -> {
                val value = stack.popRef()
                val ref = stack.popRef() ?: throw NullPointerException("ref is null")
                ref.fields.setRef(slotId, value!!)
            }
        }
    }
}
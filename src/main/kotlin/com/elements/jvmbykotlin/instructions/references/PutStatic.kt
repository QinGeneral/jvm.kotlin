package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.FieldRef

class PutStatic : Index16Instruction() {
    override fun execute(frame: Frame) {
        val curMethod = frame.method
        val curClass = curMethod.yuClass
        val constantPool = curClass.constantPool
        val fieldRef = constantPool.getConstant(index) as FieldRef
        val field = fieldRef.resolveField()
        val c = field.yuClass
        if (!field.isStatic()) {
            throw IncompatibleClassChangeError("Incompatible class ${curClass.name} ${field.name}")
        }
        if (field.isFinal()) {
            if ((curClass != c) or (curMethod.name != "<clinit>")) {
                throw IllegalAccessError("Illegal access ${c.name} ${curMethod.name}")
            }
        }

        val descriptor = field.descriptor
        val slotId = field.slotId
        val variable = c.staticVariables
        val stack = frame.operandStack
        when (descriptor[0]) {
            'Z', 'B', 'C', 'S', 'I' -> {
                variable.setInt(slotId, stack.popInt())
            }
            'F' ->
                variable.setFloat(slotId, stack.popFloat())
            'J' ->
                variable.setLong(slotId, stack.popLong())
            'D' ->
                variable.setDouble(slotId, stack.popDouble())
            'L', '[' ->
                variable.setRef(slotId, stack.popRef()!!)
        }
    }
}
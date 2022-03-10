package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.instructions.base.InvokeLogic
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.ref.InterfaceMethodRef
import com.elements.jvmbykotlin.runtimedata.heap.ref.MethodRef

/**
 * Invoke method in interface
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.invokeinterface">invokeinterface</a>
 *
 * @author hanzhang
 */
class InvokeInterface : Instruction {
    var index: Int = 0
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        index = bytecodeReader.readInt16()
        bytecodeReader.readUInt8() // arg slot count
        bytecodeReader.readUInt8() // must be 0
    }

    override fun execute(frame: Frame) {
        val constantPool = frame.method.yuClass.constantPool
        val methodRef = constantPool.getConstant(index) as InterfaceMethodRef
        val resolvedMethod = methodRef.resolveInterfaceMethod()
        if (resolvedMethod.isStatic() or resolvedMethod.isPrivate()) {
            throw IncompatibleClassChangeError()
        }
        val ref = frame.operandStack.getRefFromTop(resolvedMethod.argSlotCount - 1)
        if (ref == null) {
            throw NullPointerException()
        }
        if (!ref.yuClass.isImplements(methodRef.resolvedClass())) {
            throw IncompatibleClassChangeError()
        }
        val methodToBeInvoked = MethodRef.lookupMethodInClass(ref.yuClass, methodRef.name, methodRef.descriptor)
        if ((methodToBeInvoked == null) or methodToBeInvoked!!.isAbstract()) {
            throw AbstractMethodError()
        }
        if (!methodToBeInvoked.isPublic()) {
            throw IllegalAccessError()
        }
        InvokeLogic.invokeMethod(frame, methodToBeInvoked)
    }
}
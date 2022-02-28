package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.instructions.base.InvokeLogic
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod
import com.elements.jvmbykotlin.runtimedata.heap.ref.MethodRef

class InvokeSpecial : Index16Instruction() {
    override fun execute(frame: Frame) {
        val currentClass = frame.method.yuClass
        val constantPool = currentClass.constantPool
        val methodRef = constantPool.getConstant(index) as MethodRef
        val resolvedClass = methodRef.resolvedClass()
        val resolvedMethod = methodRef.resolveMethod()
        if ((resolvedMethod.name == "<init>") and (resolvedMethod.yuClass != resolvedClass)) {
            throw NoSuchMethodError("No such method ${resolvedMethod.name}")
        }
        if (resolvedMethod.isStatic()) {
            throw IncompatibleClassChangeError("Method is static")
        }
        val ref = frame.operandStack.getRefFromTop(resolvedMethod.argSlotCount - 1)
            ?: throw NullPointerException("Ref from top is null")
        if (resolvedMethod.isProtected()
            and resolvedMethod.yuClass.isSubClassOf(currentClass)
            and (resolvedMethod.yuClass.getPackageName() != currentClass.getPackageName())
            and (ref.yuClass != currentClass)
            and (ref.yuClass.isSubClassOf(currentClass))
        ) {
            throw IllegalAccessError("Class access error")
        }
        var methodToBeInvoked: YuMethod? = resolvedMethod
        if (currentClass.isSuper()
            and resolvedClass.isSuperClassOf(currentClass)
            and (resolvedMethod.name != "<init>")
        ) {
            methodToBeInvoked =
                MethodRef.lookupMethodInClass(currentClass.superClass, methodRef.name, methodRef.descriptor)
        }
        if ((methodToBeInvoked == null) or methodToBeInvoked!!.isAbstract()) {
            throw AbstractMethodError("Method is abstract")
        }
        InvokeLogic.invokeMethod(frame, methodToBeInvoked)
    }
}
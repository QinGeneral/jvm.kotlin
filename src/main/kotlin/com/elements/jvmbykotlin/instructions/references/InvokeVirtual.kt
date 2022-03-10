package com.elements.jvmbykotlin.instructions.references

import com.elements.jvmbykotlin.instructions.base.Index16Instruction
import com.elements.jvmbykotlin.instructions.base.InvokeLogic
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.ref.MethodRef

/**
 * Invoke instance method which is not privite
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.invokevirtual">invokevirtual</a>
 *
 * @author hanzhang
 */
class InvokeVirtual : Index16Instruction() {
    override fun execute(frame: Frame) {
        val currentClass = frame.method.yuClass
        val constantPool = currentClass.constantPool
        val methodRef = constantPool.getConstant(index) as MethodRef
        val resolvedMethod = methodRef.resolveMethod()

        if (resolvedMethod.isStatic()) {
            throw IncompatibleClassChangeError("")
        }
        val ref = frame.operandStack.getRefFromTop(resolvedMethod.argSlotCount - 1)
        if (ref == null) {
            if (methodRef.name == "println") {
                println(methodRef, frame)
                return
            }
            throw NullPointerException("Ref is null")
        }
        if (resolvedMethod.isProtected()
            and resolvedMethod.yuClass.isSuperClassOf(currentClass)
            and (resolvedMethod.yuClass.getPackageName() != currentClass.getPackageName())
            and (ref.yuClass != currentClass)
            and ref.yuClass.isSuperClassOf(currentClass)
        ) {
            throw IllegalAccessError("")
        }

        val methodToBeInvoked = MethodRef.lookupMethodInClass(ref.yuClass, methodRef.name, methodRef.descriptor)
        if ((methodToBeInvoked == null) or methodToBeInvoked!!.isAbstract()) {
            throw AbstractMethodError("")
        }
        InvokeLogic.invokeMethod(frame, methodToBeInvoked)
    }

    private fun println(
        methodRef: MethodRef,
        frame: Frame
    ) {
        if (methodRef.name == "println") {
            val stack = frame.operandStack
            when (methodRef.descriptor) {
                "(Z)V" ->
                    println("InvokeVirtual println ${stack.popInt() != 0}")
                "(C)V" ->
                    println("InvokeVirtual println ${stack.popInt()}")
                "(B)V" ->
                    println("InvokeVirtual println ${stack.popInt()}")
                "(S)V" ->
                    println("InvokeVirtual println ${stack.popInt()}")
                "(I)V" ->
                    println("InvokeVirtual println ${stack.popInt()}")
                "(J)V" ->
                    println("InvokeVirtual println ${stack.popLong()}")
                "(F)V" ->
                    println("InvokeVirtual println ${stack.popFloat()}")
                "(D)V" ->
                    println("InvokeVirtual println ${stack.popDouble()}")
                "(Ljava/lang/String;)V" -> {
                    val jStr = stack.popRef()
                    val str = if (jStr == null) "null" else InternedString.string(jStr)
                    println("InvokeVirtual println $str")
                }
                else ->
                    println("not support method ${methodRef.descriptor}")
            }
            stack.popRef()
        }
    }
}
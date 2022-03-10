package com.elements.jvmbykotlin.instructions.reserved

import com.elements.jvmbykotlin.instructions.base.NoOperationsInstruction
import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Invoke native method
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.invokenative">invokenative</a>
 *
 * @author hanzhang
 */
class InvokeNative : NoOperationsInstruction() {
    override fun execute(frame: Frame) {
        val method = frame.method
        val className = method.yuClass.name
        val methodName = method.name
        val methodDescriptor = method.descriptor
        val nativeMethod = Registry.findNativeMethod(className, methodName, methodDescriptor)
        if (nativeMethod == null) {
            val methodInfo = "$className.$methodName$methodDescriptor"
            throw UnsatisfiedLinkError(methodInfo)
        }
        nativeMethod(frame)
    }
}
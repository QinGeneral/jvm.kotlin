package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.runtimedata.heap.YuClass
import com.elements.jvmbykotlin.runtimedata.heap.YuMethod
import org.mockito.Mockito.mock

object TestUtils {
    fun mockMethod(maxLocals: Int, maxStack: Int): YuMethod {
        val method = mock(YuMethod::class.java)
        method.maxLocals = maxLocals
        method.maxStack = maxStack
        return method
    }

    fun mockClass(): YuClass {
        val c = mock(YuClass::class.java)
        return c
    }
}
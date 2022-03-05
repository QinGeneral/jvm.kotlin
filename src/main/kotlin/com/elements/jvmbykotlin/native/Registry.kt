package com.elements.jvmbykotlin.native

import com.elements.jvmbykotlin.native.java.lang.JavaClass
import com.elements.jvmbykotlin.native.java.lang.JavaObject
import com.elements.jvmbykotlin.runtimedata.Frame

object Registry {
    private val registry = mutableMapOf<String, (frame: Frame) -> Unit>()
    private var isInit = false

    fun register(
        className: String,
        methodName: String,
        methodDescriptor: String,
        nativeMethod: (frame: Frame) -> Unit
    ) {
        val key = "$className-$methodName-$methodDescriptor"
        registry[key] = nativeMethod
    }

    fun registerAll() {
        if (isInit) {
            return
        }
        val javaClass = JavaClass()
        javaClass.init()
        val javaObject = JavaObject()
        javaObject.init()
        isInit = true
    }

    fun findNativeMethod(className: String, methodName: String, methodDescriptor: String): ((frame: Frame) -> Unit)? {
        registerAll()

        val key = "$className-$methodName-$methodDescriptor"
        if (registry.containsKey(key)) {
            return registry[key]!!
        }
        if (methodDescriptor == "()V" && methodName == "registerNatives") {
            return { _: Frame -> }
        }
        return null
    }
}
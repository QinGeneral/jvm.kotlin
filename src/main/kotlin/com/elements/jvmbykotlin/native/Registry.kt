package com.elements.jvmbykotlin.native

import com.elements.jvmbykotlin.native.java.lang.*
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
        val javaObject = JavaObject()
        val javaSystem = JavaSystem()
        val javaFloat = JavaFloat()
        val javaString = JavaString()
        val javaDouble = JavaDouble()
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
package com.elements.jvmbykotlin.runtimedata

import com.elements.jvmbykotlin.runtimedata.heap.YuObject

class Slot() {
    var baseType: Int = 0
    var referenceType: YuObject? = null

    constructor(i: Int) : this() {
        baseType = i
    }

    constructor(ref: YuObject?) : this() {
        referenceType = ref
    }

    override fun toString(): String {
        return "Slot(baseType=$baseType, referenceType=$referenceType)"
    }
}
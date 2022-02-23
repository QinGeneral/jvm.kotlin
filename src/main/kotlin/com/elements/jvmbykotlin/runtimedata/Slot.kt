package com.elements.jvmbykotlin.runtimedata

class Slot() {
    var baseType: Int = 0
    var referenceType: YuObject? = YuObject()

    constructor(i: Int) : this() {
        baseType = i
    }

    constructor(ref: YuObject?) : this() {
        referenceType = ref
    }
}
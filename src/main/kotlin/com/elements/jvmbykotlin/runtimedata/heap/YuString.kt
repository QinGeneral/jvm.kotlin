package com.elements.jvmbykotlin.runtimedata.heap

class YuString(yuClass: YuClass, val value: String) : YuObject(yuClass) {
    override fun toString(): String {
        return "YuString(value='$value')"
    }
}
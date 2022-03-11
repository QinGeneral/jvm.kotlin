package com.elements.jvmbykotlin.runtimedata.heap

/**
 * Java String
 *
 * @author hanzhang
 */
class YuString(yuClass: YuClass, val value: String) : YuObject(yuClass) {
    override fun toString(): String {
        return "YuString(value='$value')"
    }
}
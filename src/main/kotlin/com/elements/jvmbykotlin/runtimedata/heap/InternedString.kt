package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.runtimedata.LocalVariable

/**
 * Java string pool for reuse string
 *
 * @author hanzhang
 */
object InternedString {
    private val internedString = mutableMapOf<String, YuObject>()

    fun internString(jString: YuObject): YuObject {
        val str = string(jString)
        if (str in internedString.keys) {
            return internedString[str]!!
        }
        internedString[str] = jString
        return jString
    }

    fun string(jString: YuObject): String {
        val charObject = jString.getRefVariable("value", "[C")
        val jChars = (charObject as ArrayObject).chars()
        return String(jChars)
    }

    fun jString(loader: YuClassLoader, str: String): YuObject {
        if (internedString.containsKey(str)) {
            return internedString[str]!!
        }

        val chars = str.toCharArray()
        val jChars = ArrayObject(loader.loadClass("[C"), chars)

        val strData = LocalVariable(1)
        val strObject = YuObject(loader.loadClass("java/lang/String"), strData)
        strObject.setRefVariable("value", "[C", jChars)
        internedString[str] = strObject

        return strObject
    }
}
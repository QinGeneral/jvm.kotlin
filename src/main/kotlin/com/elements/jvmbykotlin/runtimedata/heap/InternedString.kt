package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.runtimedata.LocalVariable

object InternedString {
    private val internedString = mutableMapOf<String, YuObject>()

    fun string(jString: YuObject): String {
        val charObject = jString.getRefVariable("value", "[C")
        val jChars = CharArray(charObject!!.fields.slots.size)
        for (i in charObject.fields.slots.indices) {
            jChars[i] = charObject.fields.getInt(i).toChar()
        }
        return String(jChars)
    }

    fun jString(loader: YuClassLoader, str: String): YuObject {
        if (internedString.containsKey(str)) {
            return internedString[str]!!
        }

        val chars = str.toCharArray()
        val data = LocalVariable(chars.size)
        for (i in chars.indices) {
            data.setInt(i, chars[i].code)
        }
        val jChars = YuObject(loader.loadClass("[C"), data)

        val strData = LocalVariable(1)
        val strObject = YuObject(loader.loadClass("java/lang/String"), strData)
        strObject.setRefVariable("value", "[C", jChars)
        internedString[str] = strObject

        return strObject
    }
}
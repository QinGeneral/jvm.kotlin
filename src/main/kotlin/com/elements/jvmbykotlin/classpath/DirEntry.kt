package com.elements.jvmbykotlin.classpath

import java.io.File

class DirEntry(private val classPath: String) : IEntry {
    override fun readClass(classname: String): ClassReadResult {
        try {
            val file = File(classPath, classname)
            val byteCode = file.readBytes()
            return ClassReadResult(byteCode, true, "no error")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ClassReadResult(null, false, "class no found $classname")
    }

    override fun toString(): String {
        return "DirEntry(classPath='$classPath')"
    }
}
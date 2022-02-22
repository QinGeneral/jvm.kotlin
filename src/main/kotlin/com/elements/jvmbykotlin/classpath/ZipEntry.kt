package com.elements.jvmbykotlin.classpath

import java.io.File
import java.util.zip.ZipFile

class ZipEntry(private val classPath: String) : IEntry {
    override fun readClass(classname: String): ClassReadResult {
        val file = File(classPath, classname)
        if (!file.isDirectory) {
            return ClassReadResult(null, false, "class no found $classname")
        }

        val zipFile = ZipFile(file)

        for (entry in zipFile.entries()) {
            if (entry.name == classname) {
                val byteCode = File(classPath, entry.name).readBytes()
                return ClassReadResult(byteCode, true, "no error")
            }
        }
        return ClassReadResult(null, false, "class no found $classname")
    }

    override fun toString(): String {
        return "ZipEntry(classPath='$classPath')"
    }
}
package com.elements.jvmbykotlin.classpath

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths

class ZipEntry(private val classPath: String) : IEntry {
    override fun readClass(classname: String): ClassReadResult {
        val absPath = Paths.get(classPath).toAbsolutePath()
        try {
            val file = FileSystems.newFileSystem(absPath, null)
            val byteCode = Files.readAllBytes(file.getPath(classname))
            return ClassReadResult(byteCode, true, "no error")
        } catch (e: Exception) {
        }
        return ClassReadResult(null, false, "class no found $classname")
    }

    override fun toString(): String {
        return "ZipEntry(classPath='$classPath')"
    }
}
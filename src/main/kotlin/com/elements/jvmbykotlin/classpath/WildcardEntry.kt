package com.elements.jvmbykotlin.classpath

import java.io.File

/**
 * class reader for jar
 *
 * @author hanzhang
 */
class WildcardEntry(classPath: String) : IEntry {
    private var entries: ArrayList<IEntry> = ArrayList()
    private val baseDir: String

    init {
        baseDir = classPath.dropLast(1)
        val dir = File(baseDir)
        dir.walk()
            .maxDepth(1)
            .filter {
                it.isFile
            }
            .filter {
                it.name.endsWith(".jar") || it.name.endsWith(".JAR")
            }.map {
                ZipEntry(it.absolutePath)
            }
            .forEach {
                entries.add(it)
            }
    }

    override fun readClass(classname: String): ClassReadResult {
        for (entry in entries) {
            val result = entry.readClass(classname)
            if (result.isSuccess) {
                return result
            }
        }
        return ClassReadResult(null, false, "class not found $classname")
    }
}
package com.elements.jvmbykotlin.classpath

import java.io.File

class EntryFactory {
    companion object {
        private val pathSeparator: String = File.pathSeparator

        fun getEntry(path: String): IEntry {
            if (path.contains(pathSeparator)) {
                return CompositeEntry(path)
            }
            if (path.endsWith("*")) {
                return WildcardEntry(path)
            }
            if (path.endsWith(".jar") || path.endsWith(".JAR")
                || path.endsWith(".zip") || path.endsWith(".ZIP")
            ) {
                return ZipEntry(path)
            }
            return DirEntry(path)
        }
    }
}
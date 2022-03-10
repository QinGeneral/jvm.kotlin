package com.elements.jvmbykotlin.classpath

/**
 * composite class reader
 *
 * @author hanzhang
 */
open class CompositeEntry(classPath: String) : IEntry {
    private var entries: ArrayList<IEntry> = ArrayList()

    init {
        val paths = classPath.split(PATH_LIST_SEPARATOR)
        for (path in paths) {
            entries.add(EntryFactory.getEntry(path))
        }
    }

    companion object {
        const val PATH_LIST_SEPARATOR = ";"
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
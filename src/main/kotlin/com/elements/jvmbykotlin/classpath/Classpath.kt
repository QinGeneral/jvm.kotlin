package com.elements.jvmbykotlin.classpath

import java.io.File
import java.nio.file.Paths

/**
 * Read class file in jre, user class path
 *
 * @author hanzhang
 * @param jrePathOption user's jre path
 * @param classpathOption user's class path
 */
class Classpath(jrePathOption: String?, classpathOption: String?) {
    lateinit var bootClasspath: IEntry
    lateinit var extClasspath: IEntry
    lateinit var userClasspath: IEntry

    init {
        parseBootAndExtClasspath(jrePathOption)
        parseUserClasspath(classpathOption)
    }

    /**
     * read class by class name
     *
     * @param classname class name
     * @return class data in byte
     */
    fun readClass(classname: String): ClassReadResult {
        val cn = "$classname.class"
        var result = bootClasspath.readClass(cn)
        if (result.isSuccess) {
            return result
        }
        result = extClasspath.readClass(cn)
        if (result.isSuccess) {
            return result
        }
        return userClasspath.readClass(cn)
    }

    /**
     * read class file in user class path
     *
     * @param classpathOption user's class path
     */
    private fun parseUserClasspath(classpathOption: String?) {
        var classpath = "."
        if (isFileExists(classpathOption)) {
            classpath = classpathOption!!
        }
        userClasspath = EntryFactory.getEntry(classpath)
    }

    /**
     * get boot and ext class path in jre
     */
    private fun parseBootAndExtClasspath(jrePathOption: String?) {
        val jreDir = getJREDir(jrePathOption)

        // jre/lib/*
        val jreLibPath = Paths.get(jreDir, "lib", "*").toString()
        bootClasspath = WildcardEntry(jreLibPath)

        // jre/lib/ext/*
        val jreExtPath = Paths.get(jreDir, "lib", "ext", "*").toString()
        extClasspath = WildcardEntry(jreExtPath)
    }

    /**
     * get jre dir
     */
    private fun getJREDir(jrePathOption: String?): String {
        if (isFileExists(jrePathOption)) {
            return jrePathOption!!
        }
        if (isFileExists("./jre")) {
            return "./jre"
        }
        val env = System.getenv("JAVA_HOME")
        println("env " + env)
        if (isFileExists(env)) {
            return Paths.get(env, "jre").toString()
        }
        throw IllegalArgumentException("JRE path not found")
    }

    private fun isFileExists(path: String?): Boolean {
        if (isTextEmpty(path)) {
            return false
        }
        return File(path).exists()
    }

    private fun isTextEmpty(string: String?): Boolean {
        return string == null || string == ""
    }
}
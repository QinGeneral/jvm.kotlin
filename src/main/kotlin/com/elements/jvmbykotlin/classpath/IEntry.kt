package com.elements.jvmbykotlin.classpath

/**
 * Class reader
 *
 * @author hanzhang
 */
interface IEntry {
    fun readClass(classname: String): ClassReadResult
}
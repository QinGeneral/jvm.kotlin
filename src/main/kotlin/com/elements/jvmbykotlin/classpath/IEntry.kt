package com.elements.jvmbykotlin.classpath


interface IEntry {
    fun readClass(classname: String): ClassReadResult
}
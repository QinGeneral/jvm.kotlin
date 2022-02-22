package com.elements.jvmbykotlin.classpath

import java.io.File


interface IEntry {
    fun readClass(classname: String): ClassReadResult
}
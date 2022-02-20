package com.elements.jvmbygo.classpath

import java.io.File


interface IEntry {
    fun readClass(classname: String): ClassReadResult
}
package com.elements.jvmbykotlin.classpath

data class ClassReadResult(val byteCode: ByteArray?, val isSuccess: Boolean, val errorMsg: String)

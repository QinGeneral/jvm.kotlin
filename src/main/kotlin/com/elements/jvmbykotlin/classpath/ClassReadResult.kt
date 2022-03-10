package com.elements.jvmbykotlin.classpath

/**
 * Class read result
 *
 * @param byteCode class file data
 * @param isSuccess is read success
 * @param errorMsg message if read failed
 */
data class ClassReadResult(val byteCode: ByteArray?, val isSuccess: Boolean, val errorMsg: String)

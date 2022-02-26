package com.elements.jvmbykotlin.instructions.base

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BytecodeReaderTest {
    @Test
    fun readUInt16() {
        val reader = BytecodeReader()
        reader.code = ByteArray(2)
        reader.code[0] = -1
        reader.code[1] = -13

        val value = reader.readInt16()
        println("value $value")
    }
}
package com.elements.jvmbykotlin.instructions.base

class BytecodeReader() {
    lateinit var code: ByteArray
    var pc: Int = 0

    fun readUInt8(): Byte {
        val b = code[pc]
        pc++
        return b
    }

    fun readInt8(): Int {
        return readUInt8().toInt()
    }

    fun readUInt16(): UInt {
        val high = readUInt8().toUInt()
        val low = readUInt8()
        return (high shl 8) or low.toUInt()
    }

    fun readInt16(): Int {
        return readUInt16().toInt()
    }

    fun readInt32(): Int {
        val byte1 = readUInt8().toInt()
        val byte2 = readUInt8().toInt()
        val byte3 = readUInt8().toInt()
        val byte4 = readUInt8().toInt()
        return (byte1 shl 24) or (byte2 shl 16) or (byte3 shl 8) or byte4
    }

    fun readInt32Array(size: Int): ArrayList<Int> {
        val array = ArrayList<Int>()
        for (i in 0 until size) {
            array.add(readInt32())
        }
        return array
    }

    fun reset(code: ByteArray, pc: Int) {
        this.code = code
        this.pc = pc
    }

    fun skipPadding() {
        while (pc % 4 != 0) {
            readUInt8()
        }
    }
}
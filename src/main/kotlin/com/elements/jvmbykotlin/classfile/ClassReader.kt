package com.elements.jvmbykotlin.classfile


class ClassReader(data: ByteArray) {
    var index = 0
    private val data: ByteArray

    init {
        this.data = data
    }

    fun readU1UByte(): UByte {
        return readByteArray(1)[0].toUByte()
    }

    fun readU1(): UShort {
        val bytes = readByteArray(1)
        return Utils.byteArrayToUShort(bytes)
    }

    fun readU2(): UShort {
        val bytes = readByteArray(2)
        return Utils.byteArrayToUShort(bytes)
    }

    fun readU4(): UInt {
        val bytes = readByteArray(4)
        return Utils.byteArrayToUInt(bytes)
    }

    fun readU4String(): String {
        val bytes = readByteArray(4)
        return Utils.byteArrayToString(bytes)
    }

    fun readU8(): UInt {
        val bytes = readByteArray(8)
        return Utils.byteArrayToUInt(bytes)
    }

    fun readU16(): UInt {
        val bytes = readByteArray(16)
        return Utils.byteArrayToUInt(bytes)
    }

    fun readByteArray(size: Int): ByteArray {
        val byteArray = ByteArray(size)
        for (i in 0 until size) {
            byteArray[i] = data[index]
            index++
        }
//        println("readByteArray $size ${byteArray.toList()}")
        return byteArray
    }
}
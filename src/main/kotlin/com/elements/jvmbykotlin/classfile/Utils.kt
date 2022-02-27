package com.elements.jvmbykotlin.classfile

import java.nio.ByteBuffer

object Utils {
    fun byteToLong(highByteArray: ByteArray, lowByteArray: ByteArray): Long {
        val byteArray = ByteArray(highByteArray.size + lowByteArray.size)
        System.arraycopy(highByteArray, 0, byteArray, 0, highByteArray.size)
        System.arraycopy(lowByteArray, 0, byteArray, highByteArray.size, lowByteArray.size)
        val buffer = ByteBuffer.wrap(byteArray)
        return buffer.long
    }

    fun byteToDouble(highByteArray: ByteArray, lowByteArray: ByteArray): Double {
        val byteArray = ByteArray(highByteArray.size + lowByteArray.size)
        System.arraycopy(highByteArray, 0, byteArray, 0, highByteArray.size)
        System.arraycopy(lowByteArray, 0, byteArray, highByteArray.size, lowByteArray.size)
        val buffer = ByteBuffer.wrap(byteArray)
        return buffer.double
    }

    fun byteToFloat(byteArray: ByteArray): Float {
        val buffer = ByteBuffer.wrap(byteArray)
        return buffer.float
    }

    fun byteArrayToUShort(bytes: ByteArray): UShort {
        var shift = 0
        var result = 0u
        for (byte in bytes.reversed()) {
            result += byte.toUByte().toUInt() shl shift
            shift += 8
        }
        return result.toUShort()
    }

    fun byteArrayToUInt(bytes: ByteArray): UInt {
        var shift = 0
        var result = 0u
        for (byte in bytes.reversed()) {
            result += byte.toUByte().toUInt() shl shift
            shift += 8
        }
        return result
    }

    fun byteArrayToString(bytes: ByteArray): String {
        return toHexString(bytes)
    }

    fun toHexString(byteArray: ByteArray): String {
        val stringBuilder = StringBuilder()
        for (byte in byteArray) {
            stringBuilder.append(Integer.toHexString((byte.toInt() and 0xFF)))
        }
        return stringBuilder.toString()
    }

    fun byteArrayToInt(bytes: ByteArray): Int {
        val buffer = ByteBuffer.wrap(bytes)
        return buffer.int
    }
}
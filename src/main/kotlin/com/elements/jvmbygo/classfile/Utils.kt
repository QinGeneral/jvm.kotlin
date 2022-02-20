package com.elements.jvmbygo.classfile

import java.nio.ByteBuffer
import java.nio.ByteOrder

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
        val buffer = ByteBuffer.allocate(bytes.size)
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        for (i in bytes.indices.reversed()) {
            buffer.put(bytes[i])
        }
        return buffer.get(0).toUShort()
    }

    fun byteArrayToUInt(bytes: ByteArray): UInt {
        val buffer = ByteBuffer.allocate(bytes.size)
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        for (i in bytes.indices.reversed()) {
            buffer.put(bytes[i])
        }
        return buffer.getInt(0).toUInt()
    }

    fun byteArrayToInt(bytes: ByteArray): Int {
        val buffer = ByteBuffer.wrap(bytes)
        return buffer.int
    }
}
package com.elements.jvmbykotlin.runtimedata

import com.elements.jvmbykotlin.runtimedata.heap.YuObject

/**
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.6.1">Local Variables</a>
 */
class LocalVariable() {
    var slots: Array<Slot> = Array(0) { Slot() }
    var maxLocals: Int = 0

    /**
     * @param maxLocals local variable array size depends on method,
     * determined at compile time and included in the class file data for each method.
     */
    constructor(maxLocals: Int) : this() {
        this.maxLocals = maxLocals
        this.slots = Array(maxLocals) { Slot() }
    }

    fun setInt(index: Int, value: Int) {
        slots[index].baseType = value
    }

    fun getInt(index: Int): Int {
        return slots[index].baseType
    }

    fun setFloat(index: Int, value: Float) {
        slots[index].baseType = value.toBits()
    }

    fun getFloat(index: Int): Float {
        return Float.fromBits(slots[index].baseType)
    }

    fun setLong(index: Int, value: Long) {
        slots[index].baseType = value.toInt()
        slots[index + 1].baseType = (value shr 32).toInt()
    }

    fun getLong(index: Int): Long {
        val low = slots[index].baseType
        val high = slots[index + 1].baseType
        return (high.toLong() shl 32) or low.toLong()
    }

    fun setDouble(index: Int, value: Double) {
        val longValue: Long = value.toBits()
        setLong(index, longValue)
    }

    fun getDouble(index: Int): Double {
        val longValue: Long = getLong(index)
        return Double.fromBits(longValue)
    }

    fun setRef(index: Int, value: YuObject?) {
        slots[index].referenceType = value
    }

    fun getRef(index: Int): YuObject? {
        return slots[index].referenceType
    }

    fun setSlot(index: Int, value: Slot) {
        slots[index] = value
    }

    fun getSlot(index: Int): Slot {
        return slots[index]
    }

    override fun toString(): String {
        return "LocalVariable maxLocals=$maxLocals, slots=\n${slots.joinToString("\n")})"
    }
}
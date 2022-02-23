package com.elements.jvmbykotlin.runtimedata

class LocalVariable(val maxLocals: Int) {
    val slots: Array<Slot>

    init {
        slots = Array(maxLocals) { Slot() }
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

    fun setRef(index: Int, value: YuObject) {
        slots[index].referenceType = value
    }

    fun getRef(index: Int): YuObject? {
        return slots[index].referenceType
    }
}
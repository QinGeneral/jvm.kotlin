package com.elements.jvmbykotlin.runtimedata

import java.util.*

class OperandStack(val maxStack: Int) {
    val slots: Stack<Slot> = Stack()

    fun pushSlot(slot: Slot) {
        slots.push(slot)
    }

    fun popSlot(): Slot {
        return slots.pop()
    }

    fun pushInt(i: Int) {
        slots.push(Slot(i))
    }

    fun popInt(): Int {
        return slots.pop().baseType
    }

    fun pushFloat(i: Float) {
        slots.push(Slot(i.toBits()))
    }

    fun popFloat(): Float {
        return Float.fromBits(slots.pop().baseType)
    }

    fun pushLong(value: Long) {
        slots.push(Slot(value.toInt()))
        slots.push(Slot((value shr 32).toInt()))
    }

    fun popLong(): Long {
        val high = slots.pop().baseType
        val low = slots.pop().baseType
        return (high.toLong() shl 32) or low.toLong()
    }

    fun pushDouble(value: Double) {
        val longValue: Long = value.toBits()
        pushLong(longValue)
    }

    fun popDouble(): Double {
        val longValue: Long = popLong()
        return Double.fromBits(longValue)
    }

    fun pushRef(value: YuObject?) {
        slots.push(Slot(value))
    }

    fun popRef(): YuObject? {
        return slots.pop().referenceType
    }

    override fun toString(): String {
        return "OperandStack maxStack=$maxStack, slots=\n${slots.joinToString("\n")}"
    }
}
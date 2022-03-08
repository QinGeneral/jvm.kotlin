package com.elements.jvmbykotlin.runtimedata

import com.elements.jvmbykotlin.runtimedata.heap.YuObject
import java.util.*

/**
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.6.2">Operand Stacks</a>
 *
 * @param maxStack depth of the operand stack depends on method,
 * determined at compile time and included in the class file data for each method.
 */
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

    // todo fix the int overflow
    fun pushLong(value: Long) {
        slots.push(Slot(value.toInt()))
        slots.push(Slot((value shr 32).toInt()))
    }

    fun popLong(): Long {
        val high = slots.pop().baseType.toLong()
        val low = slots.pop().baseType.toLong()
        return (high shl 32) or low
    }

    fun pushDouble(value: Double) {
        val longValue: Long = value.toBits()
        pushLong(longValue)
    }

    fun popDouble(): Double {
        val longValue: Long = popLong()
        return Double.fromBits(longValue)
    }

    fun pushBoolean(value: Boolean) {
        pushInt(if (value) 1 else 0)
    }

    fun popBoolean(): Boolean {
        return popInt() == 1
    }

    fun pushRef(value: YuObject?) {
        slots.push(Slot(value))
    }

    fun popRef(): YuObject? {
        return slots.pop().referenceType
    }

    fun getRefFromTop(n: Int): YuObject? {
        return slots[slots.size - n - 1].referenceType
    }

    override fun toString(): String {
        return "OperandStack maxStack=$maxStack, slots=\n${slots.joinToString("\n")}"
    }
}
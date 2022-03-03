package com.elements.jvmbykotlin.runtimedata.heap

class ArrayObject(override val yuClass: YuClass, val data: Any) : YuObject(yuClass) {
    companion object {
        fun of(yuClass: YuClass, count: Int): ArrayObject {
            when (yuClass.name) {
                "[Z" ->
                    return ArrayObject(yuClass, ByteArray(count))
                "[B" ->
                    return ArrayObject(yuClass, ByteArray(count))
                "[C" ->
                    return ArrayObject(yuClass, CharArray(count))
                "[S" ->
                    return ArrayObject(yuClass, ShortArray(count))
                "[I" ->
                    return ArrayObject(yuClass, IntArray(count))
                "[J" ->
                    return ArrayObject(yuClass, LongArray(count))
                "[F" ->
                    return ArrayObject(yuClass, FloatArray(count))
                "[D" ->
                    return ArrayObject(yuClass, DoubleArray(count))
                else ->
                    return ArrayObject(yuClass, Array<YuObject?>(count){null})
            }
        }
    }

    fun bytes(): ByteArray {
        return data as ByteArray
    }

    fun shorts(): ShortArray {
        return data as ShortArray
    }

    fun ints(): IntArray {
        return data as IntArray
    }

    fun longs(): LongArray {
        return data as LongArray
    }

    fun chars(): CharArray {
        return data as CharArray
    }

    fun floats(): FloatArray {
        return data as FloatArray
    }

    fun doubles(): DoubleArray {
        return data as DoubleArray
    }

    fun refs(): Array<YuObject?> {
        return data as Array<YuObject?>
    }

    fun arrayLength(): Int {
        when (data) {
            is ByteArray ->
                return bytes().size
            is ShortArray ->
                return shorts().size
            is IntArray ->
                return ints().size
            is LongArray ->
                return longs().size
            is CharArray ->
                return chars().size
            is FloatArray ->
                return floats().size
            is DoubleArray ->
                return doubles().size
            is Array<*> ->
                return refs().size
            else ->
                throw TypeCastException("Not a array")
        }
    }

    override fun toString(): String {
        return "ArrayObject(yuClass=${yuClass.name}, data=${dataToString()}})"
    }

    fun dataToString(): String {
        when (data) {
            is ByteArray ->
                return bytes().joinToString(", ")
            is ShortArray ->
                return shorts().joinToString(", ")
            is IntArray ->
                return ints().joinToString(", ")
            is LongArray ->
                return longs().joinToString(", ")
            is CharArray ->
                return chars().joinToString(", ")
            is FloatArray ->
                return floats().joinToString(", ")
            is DoubleArray ->
                return doubles().joinToString(", ")
            is Array<*> ->
                return refs().joinToString(", ")
            else ->
                return "Not a array"
        }
    }
}
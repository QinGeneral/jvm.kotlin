package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo

//todo
class StackMapTableAttribute(
    override val nameIndex: UShort,
    override val length: UInt,
    classReader: ClassReader,
) : AttributeInfo(nameIndex, length) {
    val numberOfEntries: UShort
    val entries: ArrayList<StackMapTableItem> = ArrayList()

    init {
        numberOfEntries = classReader.readU2()
        for (i in 0 until numberOfEntries.toInt()) {
            val frameType = classReader.readU1UByte().toInt()
            when (frameType) {
                in 0..63 ->
                    entries.add(SameFrame(frameType))
                in 64..127 ->
                    entries.add(SameLocals1StackItemFrame(frameType, classReader))
                247 ->
                    entries.add(SameLocals1StackItemFrameExtended(frameType, classReader))
                in 248..250 ->
                    entries.add(ChopFrame(frameType, classReader))
                251 ->
                    entries.add(SameFrameExtended(frameType, classReader))
                in 252..254 ->
                    entries.add(AppendFrame(frameType, classReader))
                255 ->
                    entries.add(FullFrame(frameType, classReader))
            }
        }
    }

    open class StackMapTableItem {

    }

    class SameFrame(val frameType: Int) : StackMapTableItem() {
        override fun toString(): String {
            return "SameFrame(frameType=$frameType)"
        }
    }

    class SameLocals1StackItemFrame(val frameType: Int, classReader: ClassReader) : StackMapTableItem() {
        val verificationTypeInfoStacks = Array<Int>(1) { 0 }

        init {
            verificationTypeInfoStacks[0] = classReader.readU1().toInt()
        }

        override fun toString(): String {
            return "SameLocals1StackItemFrame(frameType=$frameType, verificationTypeInfo=${verificationTypeInfoStacks.contentToString()})"
        }
    }

    class SameLocals1StackItemFrameExtended(val frameType: Int, classReader: ClassReader) : StackMapTableItem() {
        val offsetDelta: Int
        val verificationTypeInfoStacks = Array<Int>(1) { 0 }

        init {
            offsetDelta = classReader.readU2().toInt()
            verificationTypeInfoStacks[0] = classReader.readU1().toInt()
        }

        override fun toString(): String {
            return "SameLocals1StackItemFrameExtended(frameType=$frameType, offsetDelta=$offsetDelta, verificationTypeInfo=${verificationTypeInfoStacks.contentToString()})"
        }
    }

    class ChopFrame(val frameType: Int, classReader: ClassReader) : StackMapTableItem() {
        val offsetDelta: Int

        init {
            offsetDelta = classReader.readU2().toInt()
        }

        override fun toString(): String {
            return "ChopFrame(frameType=$frameType, offsetDelta=$offsetDelta)"
        }
    }

    class SameFrameExtended(val frameType: Int, classReader: ClassReader) : StackMapTableItem() {
        val offsetDelta: Int

        init {
            offsetDelta = classReader.readU2().toInt()
        }

        override fun toString(): String {
            return "ChopFrame(frameType=$frameType, offsetDelta=$offsetDelta)"
        }
    }

    class AppendFrame(val frameType: Int, classReader: ClassReader) : StackMapTableItem() {
        val offsetDelta: Int
        val verificationTypeInfoLocals: Array<Int>

        init {
            offsetDelta = classReader.readU2().toInt()
            verificationTypeInfoLocals = Array(frameType - 251) { 0 }
            for (i in verificationTypeInfoLocals.indices) {
                verificationTypeInfoLocals[i] = classReader.readU1().toInt()
            }
        }

        override fun toString(): String {
            return "ChopFrame(frameType=$frameType, offsetDelta=$offsetDelta)"
        }
    }

    class FullFrame(val frameType: Int, classReader: ClassReader) : StackMapTableItem() {
        val offsetDelta: Int
        val numberOfLocal: Int
        val verificationTypeInfoLocals: Array<Int>
        val numberOfStackItems: Int
        val verificationTypeInfoStacks: Array<Int>

        init {
            offsetDelta = classReader.readU2().toInt()
            numberOfLocal = classReader.readU2().toInt()
            verificationTypeInfoLocals = Array(numberOfLocal) { 0 }
            for (i in 0..verificationTypeInfoLocals.size) {
                verificationTypeInfoLocals[i] = classReader.readU1().toInt()
            }
            numberOfStackItems = classReader.readU2().toInt()
            verificationTypeInfoStacks = Array(numberOfStackItems) { 0 }
            for (i in 0..verificationTypeInfoStacks.size) {
                verificationTypeInfoStacks[i] = classReader.readU1().toInt()
            }
        }

        override fun toString(): String {
            return "ChopFrame(frameType=$frameType, offsetDelta=$offsetDelta)"
        }
    }

    override fun toString(): String {
        return "StackMapTableAttribute(nameIndex=$nameIndex, length=$length, numberOfEntries=$numberOfEntries, entries=$entries)"
    }
}
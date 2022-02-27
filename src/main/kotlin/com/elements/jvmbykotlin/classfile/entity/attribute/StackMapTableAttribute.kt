package com.elements.jvmbykotlin.classfile.entity.attribute

import com.elements.jvmbykotlin.classfile.ClassReader
import com.elements.jvmbykotlin.classfile.entity.AttributeInfo
import java.lang.UnsupportedOperationException

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
        val verificationTypeInfoLocals: ArrayList<VerificationTypeInfo> = ArrayList()

        init {
            offsetDelta = classReader.readU2().toInt()
            for (i in 0 until (frameType - 251)) {
                verificationTypeInfoLocals.add(VerificationTypeInfo.of(classReader))
            }
        }

        override fun toString(): String {
            return "AppendFrame(frameType=$frameType, offsetDelta=$offsetDelta, verificationTypeInfoLocals=$verificationTypeInfoLocals)"
        }

    }

    class FullFrame(val frameType: Int, classReader: ClassReader) : StackMapTableItem() {
        val offsetDelta: Int
        val numberOfLocal: Int
        val verificationTypeInfoLocals: ArrayList<VerificationTypeInfo> = ArrayList()
        val numberOfStackItems: Int
        val verificationTypeInfoStacks: ArrayList<VerificationTypeInfo> = ArrayList()

        init {
            offsetDelta = classReader.readU2().toInt()
            numberOfLocal = classReader.readU2().toInt()
            for (i in 0 until numberOfLocal) {
                verificationTypeInfoLocals.add(VerificationTypeInfo.of(classReader))
            }
            numberOfStackItems = classReader.readU2().toInt()
            for (i in 0 until numberOfStackItems) {
                verificationTypeInfoStacks.add(VerificationTypeInfo.of(classReader))
            }
        }

        override fun toString(): String {
            return "ChopFrame(frameType=$frameType, offsetDelta=$offsetDelta)"
        }
    }

    open class VerificationTypeInfo(open val tag: Int) {
        companion object {
            fun of(classReader: ClassReader): VerificationTypeInfo {
                val tag = classReader.readU1().toInt()
                when (tag) {
                    0 ->
                        return TopVariableInfo(tag)
                    1 ->
                        return IntegerVariableInfo(tag)
                    2 ->
                        return FloatVariableInfo(tag)
                    3 ->
                        return DoubleVariableInfo(tag)
                    4 ->
                        return LongVariableInfo(tag)
                    5 ->
                        return NullVariableInfo(tag)
                    6 ->
                        return UninitializedThisVariableInfo(tag)
                    7 ->
                        return ObjectVariableInfo(tag, classReader)
                    8 ->
                        return UninitializedVariableInfo(tag, classReader)
                    else ->
                        throw UnsupportedOperationException("can't handle tag $tag")
                }
            }
        }
    }

    class TopVariableInfo(override val tag: Int) : VerificationTypeInfo(tag) {
        override fun toString(): String {
            return "TopVariableInfo(tag=$tag)"
        }
    }

    class IntegerVariableInfo(override val tag: Int) : VerificationTypeInfo(tag) {
        override fun toString(): String {
            return "IntegerVariableInfo(tag=$tag)"
        }
    }

    class DoubleVariableInfo(override val tag: Int) : VerificationTypeInfo(tag) {
        override fun toString(): String {
            return "DoubleVariableInfo(tag=$tag)"
        }
    }

    class LongVariableInfo(override val tag: Int) : VerificationTypeInfo(tag) {
        override fun toString(): String {
            return "LongVariableInfo(tag=$tag)"
        }
    }

    class FloatVariableInfo(override val tag: Int) : VerificationTypeInfo(tag) {
        override fun toString(): String {
            return "FloatVariableInfo(tag=$tag)"
        }
    }

    class NullVariableInfo(override val tag: Int) : VerificationTypeInfo(tag) {
        override fun toString(): String {
            return "NullVariableInfo(tag=$tag)"
        }
    }

    class UninitializedThisVariableInfo(override val tag: Int) : VerificationTypeInfo(tag) {
        override fun toString(): String {
            return "UninitializedThisVariableInfo(tag=$tag)"
        }
    }

    class ObjectVariableInfo(override val tag: Int, classReader: ClassReader) : VerificationTypeInfo(tag) {
        val cpoolIndex: Int

        init {
            cpoolIndex = classReader.readU2().toInt()
        }

        override fun toString(): String {
            return "ObjectVariableInfo(tag=$tag, cpoolIndex=$cpoolIndex)"
        }
    }

    class UninitializedVariableInfo(override val tag: Int, classReader: ClassReader) : VerificationTypeInfo(tag) {
        val offset: Int

        init {
            offset = classReader.readU2().toInt()
        }

        override fun toString(): String {
            return "UninitializedVariableInfo(tag=$tag, offset=$offset)"
        }
    }

    override fun toString(): String {
        return "StackMapTableAttribute(nameIndex=$nameIndex, length=$length, numberOfEntries=$numberOfEntries, entries=$entries)"
    }
}
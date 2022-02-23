package com.elements.jvmbykotlin.classfile.entity.constantpool

import com.elements.jvmbykotlin.classfile.ClassReader

class ConstantPool {
    var items: MutableMap<Int, BaseConstantPoolItem> = HashMap()

    fun getItem(index: Int): BaseConstantPoolItem {
        if (items.containsKey(index)) {
            return items[index]!!
        }
        throw IllegalArgumentException("item in cp not found $index")
    }

    companion object {
        fun of(count: Int, classReader: ClassReader): ConstantPool {
            val cp = ConstantPool()
            var i = 1
            while (i < count) {
                val type = classReader.readU1()
                val item = getItemByType(type, classReader)
                cp.items[i] = item
                i += if (type == ConstantType.LONG.type || type == ConstantType.DOUBLE.type) {
                    2
                } else {
                    1
                }
                println(item.toString() + " " + i + " " + cp.items.size)
            }
            return cp
        }

        private fun getItemByType(type: UShort, classReader: ClassReader): BaseConstantPoolItem {
            when (ConstantType.of(type)) {
                ConstantType.CLASS_INFO -> {
                    val nameIndex = classReader.readU2()
                    return ClassInfo(type, nameIndex)
                }
                ConstantType.FIELD_REF -> {
                    val classIndex = classReader.readU2()
                    val nameAndTypeIndex = classReader.readU2()
                    return FieldRefInfo(type, classIndex, nameAndTypeIndex)
                }
                ConstantType.INTERFACE_METHOD_REF -> {
                    val classIndex = classReader.readU2()
                    val nameAndTypeIndex = classReader.readU2()
                    return InterfaceMethodRefInfo(type, classIndex, nameAndTypeIndex)
                }
                ConstantType.METHOD_REF -> {
                    val classIndex = classReader.readU2()
                    val nameAndTypeIndex = classReader.readU2()
                    return MethodRefInfo(type, classIndex, nameAndTypeIndex)
                }
                ConstantType.STRING -> {
                    val stringIndex = classReader.readU2()
                    return StringInfo(type, stringIndex)
                }
                ConstantType.UTF8 -> {
                    val length = classReader.readU2()
                    val bytes = classReader.readByteArray(length.toInt())
                    return Utf8Info(type, length, bytes)
                }
                ConstantType.NAME_AND_TYPE -> {
                    val nameIndex = classReader.readU2()
                    val descriptorIndex = classReader.readU2()
                    return NameAndTypeInfo(type, nameIndex, descriptorIndex)
                }
                ConstantType.INTEGER -> {
                    val bytes = classReader.readByteArray(4)
                    return IntegerInfo(type, bytes)
                }
                ConstantType.FLOAT -> {
                    val bytes = classReader.readByteArray(4)
                    return FloatInfo(type, bytes)
                }
                ConstantType.LONG -> {
                    val highBytes = classReader.readByteArray(4)
                    val lowBytes = classReader.readByteArray(4)
                    return LongInfo(type, highBytes, lowBytes)
                }
                ConstantType.DOUBLE -> {
                    val highBytes = classReader.readByteArray(4)
                    val lowBytes = classReader.readByteArray(4)
                    return DoubleInfo(type, highBytes, lowBytes)
                }
                else ->
                    return BaseConstantPoolItem(type)
            }
        }
    }
}
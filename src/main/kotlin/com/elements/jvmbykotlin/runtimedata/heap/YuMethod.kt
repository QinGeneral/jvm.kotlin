package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.entity.MemberInfo
import com.elements.jvmbykotlin.classfile.entity.MethodInfo
import com.elements.jvmbykotlin.classfile.entity.attribute.CodeAttribute
import java.lang.IllegalArgumentException

open class YuMethod(yuClass: YuClass, memberInfo: MemberInfo, attributes: CodeAttribute?) :
    ClassMember(yuClass, memberInfo) {
    var maxStack: Int = 0
    var maxLocals: Int = 0
    var code: ByteArray = ByteArray(0)
    var argSlotCount: Int = 0

    init {
        if (attributes != null) {
            maxStack = attributes.maxStack.toInt()
            maxLocals = attributes.maxLocals.toInt()
            code = attributes.code
        }
        val methodDescriptor = calcArgSlotCount()
        if (isNative()) {
            injectCodeAttribute(methodDescriptor.returnType)
        }
    }

    /**
     * fill stack, local variable and code attribute in native method
     *
     * @param returnType native method's return type
     */
    private fun injectCodeAttribute(returnType: String) {
        maxStack = 4
        maxLocals = argSlotCount
        code = when (returnType[0]) {
            'V' ->
                byteArrayOf(0xfe.toByte(), 0xb1.toByte())
            'D' ->
                byteArrayOf(0xfe.toByte(), 0xaf.toByte())
            'F' ->
                byteArrayOf(0xfe.toByte(), 0xae.toByte())
            'J' ->
                byteArrayOf(0xfe.toByte(), 0xad.toByte())
            'L', '[' ->
                byteArrayOf(0xfe.toByte(), 0xb0.toByte())
            else ->
                byteArrayOf(0xfe.toByte(), 0xac.toByte())
        }
    }

    private fun calcArgSlotCount(): MethodDescriptor {
        val descriptor = MethodDescriptor(descriptor)
        for (paramType in descriptor.paramTypes) {
            argSlotCount++
            if ((paramType == "J") or (paramType == "D")) {
                argSlotCount++
            }
        }
        if (!isStatic()) {
            argSlotCount++
        }
        return descriptor
    }


    class MethodDescriptor(descriptor: String) {
        val paramTypes: ArrayList<String> = ArrayList()
        var returnType: String = ""

        var fieldIndex = 0
        var field: String = ""
        var returnStr: String = ""

        init {
            val str = descriptor.split(")")
            field = str[0].substring(1)
            returnStr = str[1]
            println("str $descriptor $field $returnStr")
            parseParamsType()
            parseReturnType()
        }

        fun parseParamsType() {
            while (true) {
                val field = parseFieldType()
                if (field == "") {
                    break
                }
                paramTypes.add(field)
            }
        }

        fun parseReturnType() {
            if (returnStr == "V") {
                returnType = "V"
                return
            }
            field = returnStr
            fieldIndex = 0
            returnType = parseFieldType()
            if (returnType == "") {
                throw IllegalArgumentException("Method descriptor not right")
            }
        }

        fun parseFieldType(): String {
            if (fieldIndex >= field.length) {
                return ""
            }
            val ch = field[fieldIndex]
            when (ch) {
                'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z' -> {
                    fieldIndex++
                    return ch.toString()
                }
                'L' -> {
                    var result = ""
                    while (fieldIndex < field.length) {
                        result += field[fieldIndex]
                        fieldIndex++
                        if (field[fieldIndex] == ';') {
                            fieldIndex++
                            break
                        }
                    }
                    return result
                }
                '[' -> {
                    fieldIndex++
                    return ch + parseFieldType()
                }
                else ->
                    return ""
            }
        }
    }

    companion object {
        fun getMethods(yuClass: YuClass, methodInfo: ArrayList<MethodInfo>): ArrayList<YuMethod> {
            return methodInfo
                .map { item -> YuMethod(yuClass, item, item.getCodeAttribute()) }
                .toMutableList() as ArrayList<YuMethod>
        }
    }

    override fun toString(): String {
        return "YuMethod(maxStack=$maxStack, maxLocals=$maxLocals, code=${code.contentToString()})"
    }
}
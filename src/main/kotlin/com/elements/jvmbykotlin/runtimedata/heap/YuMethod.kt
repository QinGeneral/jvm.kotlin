package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.entity.MemberInfo
import com.elements.jvmbykotlin.classfile.entity.MethodInfo
import com.elements.jvmbykotlin.classfile.entity.attribute.CodeAttribute

open class YuMethod(yuClass: YuClass, memberInfo: MemberInfo, attributes: CodeAttribute?) :
    ClassMember(yuClass, memberInfo) {
    var maxStack: Int = 0
    var maxLocals: Int = 0
    var code: ByteArray = ByteArray(0)

    init {
        if (attributes != null) {
            maxStack = attributes.maxStack.toInt()
            maxLocals = attributes.maxLocals.toInt()
            code = attributes.code
        }
    }

    companion object {
        fun getMethods(yuClass: YuClass, methodInfo: ArrayList<MethodInfo>): Array<YuMethod> {
            return methodInfo
                .map { item -> YuMethod(yuClass, item, item.getCodeAttribute()) }
                .toList()
                .toTypedArray()
        }
    }

    override fun toString(): String {
        return "YuMethod(maxStack=$maxStack, maxLocals=$maxLocals, code=${code.contentToString()})"
    }
}
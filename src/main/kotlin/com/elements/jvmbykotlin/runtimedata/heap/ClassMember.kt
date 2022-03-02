package com.elements.jvmbykotlin.runtimedata.heap

import com.elements.jvmbykotlin.classfile.entity.MemberInfo

open class ClassMember(val yuClass: YuClass, memberInfo: MemberInfo) {
    var accessFlags: Int
    var name: String
    var descriptor: String

    init {
        println("accessFlags $memberInfo")
        accessFlags = memberInfo.accessFlags.toInt()
        name = memberInfo.name
        descriptor = memberInfo.descriptor
    }

    fun isStatic(): Boolean {
        return (accessFlags and AccessFlagType.ACC_STATIC.value) != 0
    }

    fun isAccessibleTo(otherClass: YuClass): Boolean {
        if (isPublic()) {
            return true
        }
        val c = yuClass
        if (isProtected()) {
            return (otherClass == c) or (otherClass.isSubClassOf(c)) or (c.getPackageName() == otherClass.getPackageName())
        }
        if (!isPrivate()) {
            return c.getPackageName() == otherClass.getPackageName()
        }
        return otherClass == c
    }

    fun isNative(): Boolean {
        return (accessFlags and AccessFlagType.ACC_NATIVE.value) != 0
    }

    fun isPublic(): Boolean {
        return (accessFlags and AccessFlagType.ACC_PUBLIC.value) != 0
    }

    fun isPrivate(): Boolean {
        return (accessFlags and AccessFlagType.ACC_PRIVATE.value) != 0
    }

    fun isProtected(): Boolean {
        return (accessFlags and AccessFlagType.ACC_PROTECTED.value) != 0
    }

    fun isFinal(): Boolean {
        return (accessFlags and AccessFlagType.ACC_FINAL.value) != 0
    }

    fun isVolatile(): Boolean {
        return (accessFlags and AccessFlagType.ACC_VOLATILE.value) != 0
    }

    fun isTransient(): Boolean {
        return (accessFlags and AccessFlagType.ACC_TRANSIENT.value) != 0
    }

    fun isAbstract(): Boolean {
        return (accessFlags and AccessFlagType.ACC_ABSTRACT.value) != 0
    }

    fun isSynthetic(): Boolean {
        return (accessFlags and AccessFlagType.ACC_SYNTHETIC.value) != 0
    }

    fun isEnum(): Boolean {
        return (accessFlags and AccessFlagType.ACC_ENUM.value) != 0
    }

    fun copyMemberInfo(memberInfo: MemberInfo) {
        accessFlags = memberInfo.accessFlags.toInt()
        name = memberInfo.name
        descriptor = memberInfo.descriptor
    }

    override fun toString(): String {
        return "ClassMember(yuClass=$yuClass, accessFlags=$accessFlags, name='$name', descriptor='$descriptor')"
    }
}
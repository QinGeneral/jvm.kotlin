package com.elements.jvmbykotlin.runtimedata.heap

enum class AccessFlagType(val value: Int) {
    // not assigned
    ACC_NOT_DEFINE(0x0000),

    // class field method
    ACC_PUBLIC(0x0001),

    // field method
    ACC_PRIVATE(0x0002),

    // field method
    ACC_PROTECTED(0x0004),

    // field method
    ACC_STATIC(0x0008),

    // class field method
    ACC_FINAL(0x0010),

    // class
    ACC_SUPER(0x0020),

    // method
    ACC_SYNCHRONIZED(0x0020),

    // field
    ACC_VOLATILE(0x0040),

    // method
    ACC_BRIDGE(0x0040),

    // field
    ACC_TRANSIENT(0x0080),

    // method
    ACC_VARARGS(0x0080),

    // method
    ACC_NATIVE(0x0100),

    // class
    ACC_INTERFACE(0x0200),

    // class method
    ACC_ABSTRACT(0x0400),

    // method
    ACC_STRICT(0x0800),

    // class field method
    ACC_SYNTHETIC(0x1000),

    // class
    ACC_ANNOTATION(0x2000),

    // class field
    ACC_ENUM(0x4000);
}
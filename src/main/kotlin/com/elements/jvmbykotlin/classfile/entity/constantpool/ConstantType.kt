package com.elements.jvmbykotlin.classfile.entity.constantpool

enum class ConstantType(val type: UShort) {
    CLASS_INFO(7u),
    FIELD_REF(9u),
    METHOD_REF(10u),
    INTERFACE_METHOD_REF(11u),
    STRING(8u),
    INTEGER(3u),
    FLOAT(4u),
    LONG(5u),
    DOUBLE(6u),
    NAME_AND_TYPE(12u),
    UTF8(1u),
    METHOD_HANDLE(15u),
    METHOD_TYPE(16u),
    INVOKE_DYNAMIC(18u);

    companion object {
        fun of(type: UShort): ConstantType {
            return values().first { it.type == type }
        }
    }
}
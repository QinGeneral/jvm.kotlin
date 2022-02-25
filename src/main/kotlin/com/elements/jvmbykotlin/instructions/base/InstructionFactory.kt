package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.instructions.comparisons.*
import com.elements.jvmbykotlin.instructions.constants.*
import com.elements.jvmbykotlin.instructions.control.Goto
import com.elements.jvmbykotlin.instructions.control.LookupSwitch
import com.elements.jvmbykotlin.instructions.control.TableSwitch
import com.elements.jvmbykotlin.instructions.conversions.*
import com.elements.jvmbykotlin.instructions.extended.GotoWidely
import com.elements.jvmbykotlin.instructions.extended.IfNonNull
import com.elements.jvmbykotlin.instructions.extended.IfNull
import com.elements.jvmbykotlin.instructions.extended.Wide
import com.elements.jvmbykotlin.instructions.loads.*
import com.elements.jvmbykotlin.instructions.math.*
import com.elements.jvmbykotlin.instructions.stack.*
import com.elements.jvmbykotlin.instructions.stores.*
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL
import com.sun.org.apache.bcel.internal.generic.LLOAD
import java.lang.UnsupportedOperationException

object InstructionFactory {
    fun create(opcode: Int): Instruction {
        when (opcode) {
            0x00 ->
                return NoOperationsInstruction()
            0x01 ->
                return AConstNullInstruction()
            0x02 ->
                return IConstM1Instruction()
            0x03 ->
                return IConst0Instruction()
            0x04 ->
                return IConst1Instruction()
            0x05 ->
                return IConst2Instruction()
            0x06 ->
                return IConst3Instruction()
            0x07 ->
                return IConst4Instruction()
            0x08 ->
                return IConst5Instruction()
            0x09 ->
                return LConst0Instruction()
            0x0a ->
                return LConst1Instruction()
            0x0b ->
                return FConst0Instruction()
            0x0c ->
                return FConst1Instruction()
            0x0d ->
                return FConst2Instruction()
            0x0e ->
                return DConst0Instruction()
            0x0f ->
                return DConst1Instruction()
            0x10 ->
                return BiPushInstruction()
            0x11 ->
                return SiPushInstruction()
            //   0x12 ->
            // 	return &LDC{}
            //   0x13 ->
            // 	return &LDC_W{}
            //   0x14 ->
            // 	return &LDC2_W{}
            0x15 ->
                return ILoad()
            0x16 ->
                return LLoad()
            0x17 ->
                return FLoad()
            0x18 ->
                return DLoad()
            0x19 ->
                return ALoad()
            0x1a ->
                return ILoad0()
            0x1b ->
                return ILoad1()
            0x1c ->
                return ILoad2()
            0x1d ->
                return ILoad3()
            0x1e ->
                return LLoad0()
            0x1f ->
                return LLoad1()
            0x20 ->
                return LLoad2()
            0x21 ->
                return LLoad3()
            0x22 ->
                return FLoad0()
            0x23 ->
                return FLoad1()
            0x24 ->
                return FLoad2()
            0x25 ->
                return FLoad3()
            0x26 ->
                return DLoad0()
            0x27 ->
                return DLoad1()
            0x28 ->
                return DLoad2()
            0x29 ->
                return DLoad3()
            0x2a ->
                return ALoad0()
            0x2b ->
                return ALoad1()
            0x2c ->
                return ALoad2()
            0x2d ->
                return ALoad3()
            //   0x2e ->
            // 	return iaload
            //   0x2f ->
            // 	return laload
            //   0x30 ->
            // 	return faload
            //   0x31 ->
            // 	return daload
            //   0x32 ->
            // 	return aaload
            //   0x33 ->
            // 	return baload
            //   0x34 ->
            // 	return caload
            //   0x35 ->
            // 	return saload
            0x36 ->
                return IStore()
            0x37 ->
                return LStore()
            0x38 ->
                return FStore()
            0x39 ->
                return DStore()
            0x3a ->
                return AStore()
            0x3b ->
                return IStore0()
            0x3c ->
                return IStore1()
            0x3d ->
                return IStore2()
            0x3e ->
                return IStore3()
            0x3f ->
                return LStore0()
            0x40 ->
                return LStore1()
            0x41 ->
                return LStore2()
            0x42 ->
                return LStore3()
            0x43 ->
                return FStore0()
            0x44 ->
                return FStore1()
            0x45 ->
                return FStore2()
            0x46 ->
                return FStore3()
            0x47 ->
                return DStore0()
            0x48 ->
                return DStore1()
            0x49 ->
                return DStore2()
            0x4a ->
                return DStore3()
            0x4b ->
                return AStore0()
            0x4c ->
                return AStore1()
            0x4d ->
                return AStore2()
            0x4e ->
                return AStore3()
            //   0x4f ->
            // 	return iastore
            //   0x50 ->
            // 	return lastore
            //   0x51 ->
            // 	return fastore
            //   0x52 ->
            // 	return dastore
            //   0x53 ->
            // 	return aastore
            //   0x54 ->
            // 	return bastore
            //   0x55 ->
            // 	return castore
            //   0x56 ->
            // 	return sastore
            0x57 ->
                return Pop()
            0x58 ->
                return Pop2()
            0x59 ->
                return Dup()
            0x5a ->
                return DupX1()
            0x5b ->
                return DupX2()
            0x5c ->
                return Dup2()
            0x5d ->
                return Dup2X1()
            0x5e ->
                return Dup2X2()
            0x5f ->
                return Swap()
            0x60 ->
                return IADD()
            0x61 ->
                return LADD()
            0x62 ->
                return FADD()
            0x63 ->
                return DADD()
            0x64 ->
                return ISUB()
            0x65 ->
                return LSUB()
            0x66 ->
                return FSUB()
            0x67 ->
                return DSUB()
            0x68 ->
                return IMUL()
            0x69 ->
                return LMUL()
            0x6a ->
                return FMUL()
            0x6b ->
                return DMUL()
            0x6c ->
                return IDIV()
            0x6d ->
                return LDIV()
            0x6e ->
                return FDIV()
            0x6f ->
                return DDIV()
            0x70 ->
                return IRem()
            0x71 ->
                return LRem()
            0x72 ->
                return FRem()
            0x73 ->
                return DRem()
            0x74 ->
                return INEG()
            0x75 ->
                return LNEG()
            0x76 ->
                return FNEG()
            0x77 ->
                return DNEG()
            0x78 ->
                return IShl()
            0x79 ->
                return LShl()
            0x7a ->
                return IShr()
            0x7b ->
                return LShr()
            0x7c ->
                return IUShr()
            0x7d ->
                return LUShr()
            0x7e ->
                return IAnd()
            0x7f ->
                return LAnd()
            0x80 ->
                return IOR()
            0x81 ->
                return LOR()
            0x82 ->
                return IXOR()
            0x83 ->
                return LXOR()
            0x84 ->
                return IInc()
            0x85 ->
                return I2L()
            0x86 ->
                return I2F()
            0x87 ->
                return I2D()
            0x88 ->
                return L2I()
            0x89 ->
                return L2F()
            0x8a ->
                return L2D()
            0x8b ->
                return F2I()
            0x8c ->
                return F2L()
            0x8d ->
                return F2D()
            0x8e ->
                return D2I()
            0x8f ->
                return D2L()
            0x90 ->
                return D2F()
            0x91 ->
                return I2B()
            0x92 ->
                return I2C()
            0x93 ->
                return I2S()
            0x94 ->
                return LCmp()
            0x95 ->
                return FCmpL()
            0x96 ->
                return FCmpG()
            0x97 ->
                return DCmpL()
            0x98 ->
                return DCmpG()
            0x99 ->
                return IfEqualZero()
            0x9a ->
                return IfNotEqualZero()
            0x9b ->
                return IfLowerThenZero()
            0x9c ->
                return IfGreaterOrEqualZero()
            0x9d ->
                return IfGreaterThenZero()
            0x9e ->
                return IfLowerOrEqualZero()
            0x9f ->
                return IfICmpEq()
            0xa0 ->
                return IfICmpNE()
            0xa1 ->
                return IfICmpLT()
            0xa2 ->
                return IfICmpGE()
            0xa3 ->
                return IfICmpGT()
            0xa4 ->
                return IfICmpLE()
            0xa5 ->
                return IfACmpEq()
            0xa6 ->
                return IfACmpNE()
            0xa7 ->
                return Goto()
            //   0xa8 ->
            // 	return &JSR{}
            //   0xa9 ->
            // 	return &RET{}
            0xaa ->
                return TableSwitch()
            0xab ->
                return LookupSwitch()
            //   0xac ->
            // 	return ireturn
            //   0xad ->
            // 	return lreturn
            //   0xae ->
            // 	return freturn
            //   0xaf ->
            // 	return dreturn
            //   0xb0 ->
            // 	return areturn
            //   0xb1 ->
            // 	return _return
            //	  0xb2 ->
            //		return &GET_STATIC{}
            //   0xb3 ->
            // 	return &PUT_STATIC{}
            //   0xb4 ->
            // 	return &GET_FIELD{}
            //   0xb5 ->
            // 	return &PUT_FIELD{}
            //	  0xb6 ->
            //		return &INVOKE_VIRTUAL{}
            //   0xb7 ->
            // 	return &INVOKE_SPECIAL{}
            //   0xb8 ->
            // 	return &INVOKE_STATIC{}
            //   0xb9 ->
            // 	return &INVOKE_INTERFACE{}
            //   0xba ->
            // 	return &INVOKE_DYNAMIC{}
            //   0xbb ->
            // 	return &NEW{}
            //   0xbc ->
            // 	return &NEW_ARRAY{}
            //   0xbd ->
            // 	return &ANEW_ARRAY{}
            //   0xbe ->
            // 	return arraylength
            //   0xbf ->
            // 	return athrow
            //   0xc0 ->
            // 	return &CHECK_CAST{}
            //   0xc1 ->
            // 	return &INSTANCE_OF{}
            //   0xc2 ->
            // 	return monitorenter
            //   0xc3 ->
            // 	return monitorexit
            0xc4 ->
                return Wide()
            //   0xc5 ->
            // 	return &MULTI_ANEW_ARRAY{}
            0xc6 ->
                return IfNull()
            0xc7 ->
                return IfNonNull()
            0xc8 ->
                return GotoWidely()
            //   0xc9 ->
            // 	return &JSR_W{}
            //   0xca -> breakpoint
            //   0xfe -> impdep1
            //   0xff -> impdep2
            else ->
                throw UnsupportedOperationException("Unsupported opcode $opcode")
        }
    }
}
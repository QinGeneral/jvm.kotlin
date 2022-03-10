package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Compare object
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.if_acmp_cond">if_acmp</a>
 *
 * @author hanzhang
 */
class IfACmpEq : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popRef()
        val value1 = frame.operandStack.popRef()
        if (value1 == value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfACmpNE : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popRef()
        val value1 = frame.operandStack.popRef()
        if (value1 != value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}
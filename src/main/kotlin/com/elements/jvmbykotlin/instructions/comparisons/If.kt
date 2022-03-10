package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Compare with zero
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.if_cond">if</a>
 *
 * @author hanzhang
 */
class IfEqualZero : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack.popInt()
        if (value == 0) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfGreaterOrEqualZero : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack.popInt()
        if (value >= 0) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfGreaterThenZero : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack.popInt()
        if (value > 0) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfLowerOrEqualZero : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack.popInt()
        if (value <= 0) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfLowerThenZero : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack.popInt()
        if (value < 0) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfNotEqualZero : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value = frame.operandStack.popInt()
        if (value != 0) {
            BranchLogic.branch(frame, offset)
        }
    }
}
package com.elements.jvmbykotlin.instructions.comparisons

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Compare two int
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.if_icmp_cond">if_icmp</a>
 *
 * @author hanzhang
 */
class IfICmpEq : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popInt()
        val value1 = frame.operandStack.popInt()
        if (value1 == value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfICmpGE : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popInt()
        val value1 = frame.operandStack.popInt()
        if (value1 >= value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfICmpGT : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popInt()
        val value1 = frame.operandStack.popInt()
        if (value1 > value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfICmpLE : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popInt()
        val value1 = frame.operandStack.popInt()
        if (value1 <= value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfICmpLT : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popInt()
        val value1 = frame.operandStack.popInt()
        if (value1 < value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}

class IfICmpNE : BranchInstruction() {
    override fun execute(frame: Frame) {
        val value2 = frame.operandStack.popInt()
        val value1 = frame.operandStack.popInt()
        if (value1 != value2) {
            BranchLogic.branch(frame, offset)
        }
    }
}
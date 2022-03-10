package com.elements.jvmbykotlin.instructions.extended

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * If not null
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.ifnonnull">ifnonnull</a>
 *
 * @author hanzhang
 */
class IfNonNull : BranchInstruction() {
    override fun execute(frame: Frame) {
        val ref = frame.operandStack.popRef()
        if (ref != null) {
            BranchLogic.branch(frame, offset)
        }
    }
}
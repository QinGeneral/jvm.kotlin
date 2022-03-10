package com.elements.jvmbykotlin.instructions.control

import com.elements.jvmbykotlin.instructions.base.BranchInstruction
import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Go to target pc
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.goto">goto</a>
 *
 * @author hanzhang
 */
open class Goto : BranchInstruction() {
    override fun execute(frame: Frame) {
        BranchLogic.branch(frame, offset)
    }
}

class GotoWide : Goto() {
    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        offset = bytecodeReader.readInt32()
    }
}
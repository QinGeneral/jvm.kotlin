package com.elements.jvmbykotlin.instructions.control

import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Go to target pc by key
 * Refer to <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.10.1.9.tableswitch">tableswitch</a>
 *
 * @author hanzhang
 */
class TableSwitch : Instruction {
    val DEFAULT_OFFSET: Int = 0
    var low: Int = 0
    var high: Int = 0
    lateinit var jumpOffsets: ArrayList<Int>

    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        bytecodeReader.skipPadding()
        low = bytecodeReader.readInt32()
        high = bytecodeReader.readInt32()
        val jumpOffsetsCount = high - low + 1
        jumpOffsets = bytecodeReader.readInt32Array(jumpOffsetsCount)
    }

    override fun execute(frame: Frame) {
        val index = frame.operandStack.popInt()
        var offset = 0
        offset = if ((index >= low) and (index <= high)) {
            jumpOffsets[index - low]
        } else {
            DEFAULT_OFFSET
        }
        BranchLogic.branch(frame, offset)
    }
}
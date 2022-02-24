package com.elements.jvmbykotlin.instructions.control

import com.elements.jvmbykotlin.instructions.base.BranchLogic
import com.elements.jvmbykotlin.instructions.base.BytecodeReader
import com.elements.jvmbykotlin.instructions.base.Instruction
import com.elements.jvmbykotlin.runtimedata.Frame

class LookupSwitch : Instruction {
    var defaultOffset: Int = 0
    var nPairs: Int = 0
    lateinit var matchOffsets: ArrayList<Int>

    override fun fetchOperands(bytecodeReader: BytecodeReader) {
        bytecodeReader.skipPadding()
        defaultOffset = bytecodeReader.readInt32()
        nPairs = bytecodeReader.readInt32()
        matchOffsets = bytecodeReader.readInt32Array(nPairs * 2)
    }

    override fun execute(frame: Frame) {
        val key = frame.operandStack.popInt()
        for (i in 0 until (nPairs * 2) step 2) {
            if (matchOffsets[i] == key) {
                val offset = matchOffsets[i + 1]
                BranchLogic.branch(frame, offset)
                return
            }
        }
        BranchLogic.branch(frame, defaultOffset)
    }
}
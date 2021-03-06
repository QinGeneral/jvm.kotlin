package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame

/**
 * Branch execute logic for PC
 *
 * @author hanzhang
 */
class BranchLogic {
    companion object {
        fun branch(frame: Frame, offset: Int) {
            val pc = frame.thread.pc
            val nextPC = pc + offset
            frame.nextPC = nextPC
        }
    }
}
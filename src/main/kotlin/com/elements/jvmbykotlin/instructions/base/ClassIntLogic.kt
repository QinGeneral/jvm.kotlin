package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.YuClass

object ClassIntLogic {
    fun initClass(yuThread: YuThread, yuClass: YuClass) {
        yuClass.startInit()
        scheduleClinit(yuThread, yuClass)
        initSuperClass(yuThread, yuClass)
    }

    fun scheduleClinit(yuThread: YuThread, yuClass: YuClass) {
        val clinit = yuClass.getClinitMethod()
        if (clinit != null) {
            val newFrame = Frame(yuThread, clinit)
            yuThread.pushFrame(newFrame)
        }
    }

    fun initSuperClass(yuThread: YuThread, yuClass: YuClass) {
        if (!yuClass.isInterface()) {
            val superClass = yuClass.superClass
            if ((superClass != null) && !(superClass.isInitStarted)) {
                initClass(yuThread, superClass)
            }
        }
    }
}
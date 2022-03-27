package com.elements.jvmbykotlin.instructions.base

import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.YuClass

/**
 * init the class: invoke clinit method and init super class
 *
 * @author hanzhang
 */
object ClassInitLogic {
    /**
     * init class
     *
     * @param yuThread current thread
     * @param yuClass current class
     */
    fun initClass(yuThread: YuThread, yuClass: YuClass) {
        yuClass.startInit()
        scheduleClinit(yuThread, yuClass)
        initSuperClass(yuThread, yuClass)
    }

    /**
     * schedule to run class initialization method
     *
     * @param yuThread on this thread
     * @param yuClass initialize this class
     */
    private fun scheduleClinit(yuThread: YuThread, yuClass: YuClass) {
        val clinit = yuClass.getClinitMethod()
        if (clinit != null) {
            val newFrame = Frame(yuThread, clinit)
            yuThread.pushFrame(newFrame)
        }
    }

    /**
     * init super class
     *
     * @param yuThread current thread
     * @param yuClass current class
     */
    private fun initSuperClass(yuThread: YuThread, yuClass: YuClass) {
        if (!yuClass.isInterface()) {
            val superClass = yuClass.superClass
            if ((superClass != null) && !(superClass.isInitStarted)) {
                initClass(yuThread, superClass)
            }
        }
    }
}
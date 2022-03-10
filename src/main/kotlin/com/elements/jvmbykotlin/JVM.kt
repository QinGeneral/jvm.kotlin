package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.classpath.Classpath
import com.elements.jvmbykotlin.instructions.base.ClassInitLogic
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.YuClassLoader

/**
 * jvm.kotlin
 *
 * @author hanzhang
 * @param cmd parsed command
 */
class JVM(private val cmd: Cmd) {
    private val classloader: YuClassLoader
    private val mainThread: YuThread
    private val interpreter = Interpreter()

    init {
        val classpath = Classpath(cmd.jrePathOption, cmd.classPathOption)
        classloader = YuClassLoader(classpath, cmd.verbose)
        mainThread = YuThread()
    }

    /**
     * start the vm
     */
    fun start() {
        initVM()
        executeMain()
    }

    /**
     * init vm, invoke VM method to init some Java Class
     */
    private fun initVM() {
        val vmClass = classloader.loadClass("sun/misc/VM")
        ClassInitLogic.initClass(mainThread, vmClass)
        interpreter.interpret(mainThread, cmd.versionFlag)
    }

    /**
     * execute main method in Java Class
     */
    private fun executeMain() {
        val classname = cmd.className.replace(".", "/")
        val mainClass = classloader.loadClass(classname)
        val mainMethod = mainClass.getMainMethod()
        if (mainMethod == null) {
            println("Main method not found in class ${cmd.className}")
            return
        }

        val args = if (cmd.args is ArrayList<*>)
            cmd.args as ArrayList<String>
        else
            ArrayList()
        val argsArray = createArgsArray(args)

        val frame = Frame(mainThread, mainMethod)
        frame.localVariable.setRef(0, argsArray)
        mainThread.pushFrame(frame)

        interpreter.interpret(mainThread, cmd.verbose)
    }

    /**
     * convert command args to Java main method args
     *
     * @param args command args
     * @return ArrayObject in jvm.kotlin
     */
    private fun createArgsArray(args: ArrayList<String>): ArrayObject {
        val stringClass = classloader.loadClass("java/lang/String")
        val argsArray = ArrayObject.of(stringClass.getArrayClass(), args.size)
        val jArgs = argsArray.refs()
        for (i in 0 until args.size) {
            jArgs[i] = InternedString.jString(classloader, args[i])
        }
        return argsArray
    }
}
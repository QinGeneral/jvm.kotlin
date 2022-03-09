package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.classpath.Classpath
import com.elements.jvmbykotlin.instructions.base.ClassIntLogic
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.ArrayObject
import com.elements.jvmbykotlin.runtimedata.heap.InternedString
import com.elements.jvmbykotlin.runtimedata.heap.YuClassLoader

class JVM(val cmd: Cmd) {
    val classloader: YuClassLoader
    val mainThread: YuThread
    val interpreter = Interpreter()

    init {
        val classpath = Classpath(cmd.jrePathOption, cmd.classPathOption)
        classloader = YuClassLoader(classpath, cmd.verbose)
        mainThread = YuThread()
    }


    fun startJVM() {

    }

    fun start() {
        initVM()
        executeMain()
    }

    private fun initVM() {
        val vmClass = classloader.loadClass("sun/misc/VM")
        ClassIntLogic.initClass(mainThread, vmClass)
        interpreter.interpret(mainThread, cmd.versionFlag)
    }

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
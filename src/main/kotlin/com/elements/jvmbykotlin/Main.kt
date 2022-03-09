package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.classpath.Classpath
import com.elements.jvmbykotlin.runtimedata.heap.YuClassLoader
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = mainBody {
            val cmd = ArgParser(args).parseInto(::Cmd)

            if (cmd.versionFlag && cmd.className == "") {
                cmd.printVersion()
            } else {
                println(cmd)
                startJVM(cmd)
            }
        }

        fun startJVM(cmd: Cmd) {
            val classpath = Classpath(cmd.jrePathOption, cmd.classPathOption)
            val classloader = YuClassLoader(classpath, cmd.verbose)
            val classname = cmd.className.replace(".", "/")
            val mainClass = classloader.loadClass(classname)
            val mainMethod = mainClass.getMainMethod()
            if (mainMethod == null) {
                println("Main method not found in class ${cmd.className}")
            }
            val interpreter = Interpreter()
            val args = if (cmd.args is ArrayList<*>)
                cmd.args as ArrayList<String>
            else
                ArrayList()
            interpreter.interpret(mainMethod!!, cmd.verbose, args)
        }
    }
}

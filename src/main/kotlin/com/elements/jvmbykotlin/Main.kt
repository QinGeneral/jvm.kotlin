package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.classfile.ClassFile
import com.elements.jvmbykotlin.classpath.Classpath
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
            val classname = cmd.className.replace(".", "/")

            val result = classpath.readClass(classname)
            if (!result.isSuccess) {
                println("Could not find or load class ${cmd.className}")
                return
            }

            val classFile = ClassFile()
            classFile.parse(result.byteCode!!)
            val interpreter = Interpreter()
            interpreter.interpret(classFile.methods[2])

            println("class file $classFile")
        }
    }
}

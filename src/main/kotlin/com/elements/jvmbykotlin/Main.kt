package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.classfile.ClassFile
import com.elements.jvmbykotlin.classfile.entity.MethodInfo
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

            val mainMethodInfo = getMainMethod(classFile)
            if (mainMethodInfo == null) {
                println("main method not found")
                return
            }
            interpreter.interpret(mainMethodInfo)

            println("class file $classFile")
        }

        fun getMainMethod(classFile: ClassFile): MethodInfo? {
            for (methodInfo in classFile.methods) {
                if ((methodInfo.name == "main") and (methodInfo.descriptor == "([Ljava/lang/String;)V")) {
                    return methodInfo
                }
            }
            return null
        }
    }
}

package com.elements.jvmbykotlin

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
                JVM(cmd).start()
            }
        }
    }
}

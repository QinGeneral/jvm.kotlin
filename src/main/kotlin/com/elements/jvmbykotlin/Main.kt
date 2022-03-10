package com.elements.jvmbykotlin

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

/**
 * Parse command args, and start jvm
 *
 * @author hanzhang
 */
class Main {
    companion object {
        /**
         * Main method, the door of jvm.kotlin
         *
         * @param args command and args
         */
        @JvmStatic
        fun main(args: Array<String>) = mainBody {
            val cmd = ArgParser(args).parseInto(::Cmd)

            if (cmd.versionFlag && cmd.className == "") {
                cmd.printVersion()
            } else {
                JVM(cmd).start()
            }
        }
    }
}

package com.elements.jvmbykotlin

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class Cmd(parser: ArgParser) {
    val versionFlag by parser.flagging(
        "-v", "--version",
        help = "tool version"
    )
    val verbose by parser.flagging(
        "-l", "--verbose",
        help = "show log"
    )
    val classPathOption by parser.storing(
        "-p", "--path",
        help = "path to class"
    )
    val className by parser.storing(
        "-c", "--classname",
        help = "class name"
    )
    val args by parser.positionalList(
        "ARGS",
        help = "args"
    ).default("")

    val jrePathOption by parser.storing(
        "--Xjre",
        help = "path to jre"
    )


    fun printVersion() {
        println("version 0.0.1")
    }

    override fun toString(): String {
        return "Cmd(versionFlag=$versionFlag, verbose=$verbose, classPathOption='$classPathOption', className='$className', args=$args, jrePathOption='$jrePathOption')"
    }
}
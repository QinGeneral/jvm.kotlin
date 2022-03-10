package com.elements.jvmbykotlin

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

/**
 * Command parser, parse by ArgParser
 *
 * @author hanzhang
 */
class Cmd(parser: ArgParser) {
    /**
     * print jvm version if add this option
     */
    val versionFlag by parser.flagging(
        "-v", "--version",
        help = "tool version"
    )

    /**
     * print logs if add this option
     */
    val verbose by parser.flagging(
        "-l", "--verbose",
        help = "show log"
    )

    /**
     * config the class path by this option
     */
    val classPathOption by parser.storing(
        "-p", "--path",
        help = "path to class"
    )

    /**
     * config the class name by this option
     */
    val className by parser.storing(
        "-c", "--classname",
        help = "class name"
    )

    /**
     * input the args for Java main method by this option
     */
    val args by parser.positionalList(
        "ARGS",
        help = "args"
    ).default("")

    /**
     * config the jre path by this option, read the system env by default
     */
    val jrePathOption by parser.storing(
        "--Xjre",
        help = "path to jre"
    )

    /**
     * print jvm version
     */
    fun printVersion() {
        println("version 0.0.1")
    }

    override fun toString(): String {
        return "Cmd(versionFlag=$versionFlag, verbose=$verbose, classPathOption='$classPathOption', className='$className', args=$args, jrePathOption='$jrePathOption')"
    }
}
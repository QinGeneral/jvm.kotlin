package com.elements.jvmbygo

import com.xenomachina.argparser.ArgParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CmdTest {
    private val path = "/Users/zhanghan/Code/IDEAProjects/JVMByKotlin/build/classes/java/main/"
    private val className = "Main"
    private val jrePath = "/Users/zhanghan/.sdkman/candidates/java/current"
    private val args = arrayOf(
        "-v", "-p", path, "-c", className, "--Xjre", jrePath
    )
    lateinit var cmd: Cmd

    @BeforeEach
    fun setUp() {
        cmd = ArgParser(args).parseInto(::Cmd)
    }

    @Test
    fun testStartVM() {
        // WHEN
        Main.startJVM(cmd)
    }

    @Test
    fun testCmdParser() {
        // WHEN
        val cmd = ArgParser(args).parseInto(::Cmd)
        println(cmd)

        // THEN
        Assertions.assertEquals(path, cmd.classPathOption)
        Assertions.assertEquals(className, cmd.className)
    }
}

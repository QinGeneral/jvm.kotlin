package com.elements.jvmbykotlin

import com.elements.jvmbykotlin.runtimedata.heap.YuObject
import com.xenomachina.argparser.ArgParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CmdTest {
    private val path = "${System.getProperty("user.dir")}/build/classes/java/main/"
    private val className = "Main"

    private val jrePath = "/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/jre"
    private val args = arrayOf(
        "-v", "-p", path, "-c", className, "--Xjre", jrePath, "-l", "hello kvm 1024"
    )
    lateinit var cmd: Cmd

    @BeforeEach
    fun setUp() {
        cmd = ArgParser(args).parseInto(::Cmd)
    }

    @Test
    fun testStartVM() {
        // WHEN
        JVM(cmd).start()
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

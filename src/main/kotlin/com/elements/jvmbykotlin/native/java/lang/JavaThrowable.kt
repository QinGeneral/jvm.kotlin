package com.elements.jvmbykotlin.native.java.lang

import com.elements.jvmbykotlin.native.Registry
import com.elements.jvmbykotlin.runtimedata.Frame
import com.elements.jvmbykotlin.runtimedata.YuThread
import com.elements.jvmbykotlin.runtimedata.heap.YuClass
import com.elements.jvmbykotlin.runtimedata.heap.YuObject

class JavaThrowable {
    init {
        Registry.register(
            "java/lang/Throwable", "fillInStackTrace",
            "(I)Ljava/lang/Throwable;"
        ) { frame: Frame -> fillInStackTrace(frame) }
    }

    private fun fillInStackTrace(frame: Frame) {
        val thiz = frame.localVariable.getThis()
        frame.operandStack.pushRef(thiz)
        thiz.extra = createStackTraceElements(thiz, frame.thread)
    }

    private fun createStackTraceElements(topObject: YuObject, thread: YuThread): ArrayList<StackTraceElement> {
        val skip = distanceToObject(topObject.yuClass) + 2
        val stackInfo = ArrayList<StackTraceElement>()
        for (i in skip until thread.stack.size) {
            stackInfo.add(createStackTraceElement(thread.stack[i]))
        }
        return stackInfo
    }

    private fun createStackTraceElement(frame: Frame): StackTraceElement {
        val method = frame.method
        val yuClass = method.yuClass
        return StackTraceElement(
            yuClass.sourceFile,
            yuClass.javaName,
            method.name,
            method.getLineNumber(frame.nextPC - 1)
        )
    }

    private fun distanceToObject(yuClass: YuClass): Int {
        var distance = 0
        var c = yuClass.superClass
        while (c != null) {
            distance++
            c = c.superClass
        }
        return distance
    }

    class StackTraceElement(
        val fileName: String,
        val className: String,
        val methodName: String,
        val lineNumber: Int
    ) {
        override fun toString(): String {
            return "StackTraceElement(fileName='$fileName', className='$className', methodName='$methodName', lineNumber=$lineNumber)"
        }
    }
}
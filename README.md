# jvm.kotlin

A Java Virtual Machine written in Kotlin.

## Introduction

jvm.kotlin is a toy JVM programmed in Kotlin. The main purpose of this project is learning Kotlin and JVM.

![logo](logo.jpg)

## Develop environment

- macOS Monterey 12.2.1
- Java 1.8.0_231
- Kotlin 1.6.0

## Build jvm.kotlin

```shell
git clone https://github.com/QinGeneral/jvm.kotlin.git
cd jvm.kotlin
./gradlew installDist
```

## Run jvm.kotlin with your JDK 1.8

```shell
build/install/JVMByKotlin/bin/JVMByKotlin -p "build/classes/java/main/" -c "Main" --Xjre ""
```

or

Run the unit test in Class `CmdTest`.

> 中文版请[点击这里](README_CN.md)。
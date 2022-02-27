# jvm.kotlin

一个使用 Kotlin 实现的 Java 虚拟机。

## 简介

jvm.kotlin 使用 Kotlin 编写的轻量版 JVM。主要用于 JVM 原理和 Kotlin 语言的学习。

![logo](logo.jpg)

## 开发环境

- macOS Monterey 12.2.1
- Java 1.8.0_231
- Kotlin 1.6.0

## 编译 jvm.kotlin

```shell
git clone https://github.com/QinGeneral/jvm.kotlin.git
cd jvm.kotlin
./gradlew installDist
```

## 运行 jvm.kotlin

> 需要安装 JDK 1.8

### 方法 1

使用编译阶段的产物，运行以下命令。

```shell
build/install/JVMByKotlin/bin/JVMByKotlin -p "build/classes/java/main/" -c "Main" --Xjre ""
```

### 方法 2

运行 `CmdTest` 类中的单元测试。

> For English version, [please click here](README.md)。
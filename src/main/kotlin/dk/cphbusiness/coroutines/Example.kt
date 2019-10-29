package dk.cphbusiness.coroutines

import kotlin.concurrent.thread

fun main() {
    thread {
        Thread.sleep(1000)
        println("World!")
        }
    println("Hello")
    }
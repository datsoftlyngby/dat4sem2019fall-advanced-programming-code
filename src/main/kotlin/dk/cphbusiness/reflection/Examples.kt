package dk.cphbusiness.reflection

import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

class Dog {
    fun sayHello(greeter: String) = "Vov"
    }
class Person(val id: Int, var firstName: String, var lastName: String, var age: Int) {
    fun sayHello(greeter: String) = "Hello $firstName from $greeter"
    fun sayHello(greeter: String, count: Int) = "Hello $firstName a $count times from $greeter"
    }
class Stone {
    fun dontSayHello() = "Shh..."
    }


fun main() {
    val kurt = Person(7, "Kurt", "Hansen", 27)
    val rufus = Dog()
    val stone = Stone()
    /*
    val type = kurt::class

    println(type)
    for (member in type.members) {
        println(member)
        }
    println("--------------")
    type.members.forEach { println(it.name) }
    println("--------------")
    println(kurt.sayHello("Sonja"))
    println(kurt.sayHello("Sonja", 1000))
    */
    sayHelloIfPossible(kurt)
    println("=======")
    sayHelloIfPossible(rufus)
    println("=======")
    sayHelloIfPossible(stone)
    println()
    println(toJson(kurt))


    }


fun sayHelloIfPossible(some: Any) {
    val type = some::class
    println(type.simpleName)
    val saying = type.memberFunctions
        .filter { it.name == "sayHello" && it.parameters.size == 2 }
        .firstOrNull()
    if (saying == null) {
        println("Sorry no message")
        return
        }
    println(saying.call(some, "Sonja"))
    }

fun toJson(what: Any) =
    what::class.memberProperties
        .map { """  "${it.name}": ${jsonValueOf(it.call(what))}""" }
        .joinToString(",\n", "{\n", "\n}\n")

fun toJson2(what: Any) : String {
    var json = ""
    val type = what::class
    for (property in type.memberProperties) {
        val name = property.name
        val value = property.call(what)
        if (json.isEmpty()) json = "{\n  \"$name\": \"$value\""
        else json += ",\n  \"$name\": \"$value\""
        }
  return json+"\n}\n"
  }

fun jsonValueOf(value: Any?) =
    when (value) {
        null -> "null"
        is Int -> value.toString()
        is Double -> value.toString()
        else -> """"$value""""
        }
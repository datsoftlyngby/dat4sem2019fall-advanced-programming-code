package dk.cphbusiness.recap

import java.lang.RuntimeException

// class Gamer(val id: Int, val name: String, val score: Double)
data class Gamer(val id: Int, val name: String, val score: Double)

sealed class Result {
  class Ok(val message: String) : Result()
  class Err(val cause: Exception) : Result()
  }

fun testDataClasses() {
  val kurt = Gamer(7, "Kurt", 34.8)
  println(kurt)
  val (i, n, s) = kurt
  println("$n har id: $i og har skoret $s point")
  println(kurt.component1())
  println(kurt.component2())
  println(kurt.component3())
  }

fun printResult(result: Result) {
  when (result) {
    is Result.Ok -> println(result.message)
    is Result.Err -> println(result.cause.message)
    }
  }

fun testSealedClasses() {
  val result1 = Result.Ok("Super")
  val result2 = Result.Err(RuntimeException("Æv"))

  printResult(result1)
  printResult(result2)

  }

fun String.utf8Length() = this.toByteArray(Charsets.UTF_8).size

fun main() {
  // testDataClasses()
  // testSealedClasses()
  val food = "Jörg" // Blåbærsyletøj
  println(food.length)
  println(food.utf8Length())
  val bytes = food.toByteArray(Charsets.UTF_8)
  println(bytes.size)
  }
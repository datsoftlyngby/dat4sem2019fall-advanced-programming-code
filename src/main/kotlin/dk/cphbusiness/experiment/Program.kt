package dk.cphbusiness.experiment

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import dk.cphbusiness.coroutines.server.readLine
import dk.cphbusiness.coroutines.server.readString
import dk.cphbusiness.coroutines.server.write
import dk.cphbusiness.coroutines.server.writeLine


fun inputTest() {
  println("--- Testing input  ---")
  val data = """
      GET /member/7 HTTP/1.1
      Content-Type: text/html; charset=UTF-8
      Content-Length: 14
      
      Hellå
      Wørld!

      """.trimIndent()
  val input = data.byteInputStream()
  println(input.readLine())
  println(input.readLine())
  println(input.readLine())
  println(input.readLine())
  // println(Charsets.UTF_8.decode(ByteBuffer.wrap(input.readBytes(14))))
  println(input.readString(14))
  }

fun outputTest() {
  println("--- Testing output ---")
  val output = ByteOutputStream(1024)
  output
    .writeLine("PUT /member HTTP/1.1")
    .writeLine("Content-Type: application/json; charset=UTF-8")
    .writeLine("Content-Length: 15")
    .writeLine()
    .write("\"¡Hællo World!\"")
  println("${output}<<")
  }

fun main() {
  inputTest()
  outputTest()
  }
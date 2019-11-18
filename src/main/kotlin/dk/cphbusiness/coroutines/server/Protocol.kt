package dk.cphbusiness.coroutines.server

import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.StringBuilder

enum class Method { GET, PUT, POST, DELETE }

class Protocol {
  }

class Request(input: InputStream) {
  val resource: String
  val method: Method
  val body: String
  val headers = mutableMapOf<String, String>()

  init {
    var line = input.readLine()
    val parts = line.split(" ")
    resource = parts[1]
    method = Method.valueOf(parts[0])
    line = input.readLine()
    while (line.isNotEmpty()) {
      val headerParts = line.split(":")
      headers[headerParts[0].trim()] = headerParts[1].trim()
      line = input.readLine()
      }

    // read headers here and get Content-Length
    val contentLenght = headers["Content-Length"]!!.toInt()
    body = input.readString(contentLenght)
    }
  }

class Response(private val output: OutputStream) {
  val body = StringBuilder()

  fun append(text: String) {
    body.append(text)
    }

  fun send() {
    val head = """
        HTTP/1.1 200 OK
        Content-Type: text/html; charset=UTF-8
        Content-Length: ${body.length}
        Connection: close
        
        """.trimIndent()
    val writer = output.bufferedWriter()
    writer.append(head)
    writer.newLine()
    writer.append(body)
    writer.close()
    }

  }
/*
fun main() {
  val output = ByteArrayOutputStream(1024)
  val writer = output.bufferedWriter()
  }
 */
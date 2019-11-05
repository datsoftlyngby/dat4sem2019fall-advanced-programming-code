package dk.cphbusiness.coroutines.server

import java.io.InputStream

enum class Method { GET, PUT, POST, DELETE }

class Request(input: InputStream) {
  val resource: String
  val method: Method
  init {
    val reader = input.bufferedReader()
    val line = reader.readLine()
    val parts = line.split(" ")
    resource = parts[1]
    method = Method.valueOf(parts[0])
    }
  }
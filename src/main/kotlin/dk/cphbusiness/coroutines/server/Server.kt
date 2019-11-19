package dk.cphbusiness.coroutines.server

import java.net.ServerSocket
import kotlin.concurrent.thread

class Server(val port: Int = 4711) {
  var running = true
  val serverSocket = ServerSocket(port)

  fun handle(request: Request, response: Response) {
    println(request.resource)
    // TODO("Implement!")
    response.send()
    }

  fun start() {
    while (running) {
      val socket = serverSocket.accept()
      thread {
        handle(Request(socket.getInputStream()), Response(socket.getOutputStream()))
        }
      }
    }

  fun stop() {
    running = false
    serverSocket.close()
    }

  }

fun main() {
  println("Starting server")
  val server = Server()
  thread { server.start() }
  var line = readLine()
  while (line != null) {
    when (line) {
      "stop" -> server.stop()
      else -> println( "czczc")
      }
    line = readLine()
    }
  }
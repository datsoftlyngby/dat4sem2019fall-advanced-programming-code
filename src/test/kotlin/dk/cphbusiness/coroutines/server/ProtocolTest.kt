package dk.cphbusiness.coroutines.server

import org.junit.Assert.assertEquals
import org.junit.Test

class ProtocolTest {
  val requestText = """
      GET /greeter HTTP/1.1
      
      """.trimIndent()

  @Test
  fun testRequestResource() {
    val input = requestText.byteInputStream()
    val request = Request(input)
    assertEquals("/greeter", request.resource)
    }

  @Test
  fun testRequestMethod() {
    val input = requestText.byteInputStream()
    val request = Request(input)
    assertEquals(Method.GET, request.method)
   }

  }


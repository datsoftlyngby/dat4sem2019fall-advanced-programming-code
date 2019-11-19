package dk.cphbusiness.coroutines.server

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.memberFunctions

interface WebContent {
  fun save()
  }

class WebServer(val content: WebContent, val port: Int) {
  fun start() { TODO("Implement start") }
  fun stop() { TODO("Implement stop") }
  }


data class Member(val id: Int, val name: String)

class ChoirContent : WebContent {
  val members = mutableMapOf<Int, Member>(
    7 to Member(7, "Kurt"),
    17 to Member(17, "Sonja")
    )
  fun getMember(): List<Member> = members.values.toList()

  fun getMember(id: Int): Member? = members[id]

  fun putMember(member: Member): Member =
      TODO("Implement PUT /member")
  // ...
  override fun save() {
    TODO("implement function save")
    }
  }

fun listFunctions(content: Any) {
  val contentType = content::class
  println(contentType.simpleName)
  contentType.memberFunctions.forEach {
    println(it)
    }
  }

fun callFunction(content: Any, method: Method, resource: String, body: String) : Any? {
  val parts = (resource.split("/") + body).filter { !it.isEmpty() }
  println(parts)
  if (parts.size == 0) return null

  val methodName = method.toString().toLowerCase() + (parts[0].capitalize())
  println(methodName)
  val type = content::class
  val function = type.declaredFunctions
    .filter { it.name == methodName }
    .filter { it.parameters.size == parts.size }
    .firstOrNull()
  if (function == null) return null
  if (function.parameters.size > 1) {
    val p = function.parameters[1]
    println(p.type.classifier)
    when (p.type.classifier) {
      Int::class -> {
        val v1 = parts[1].toInt()
        return function.call(content, v1)
        }
      else -> return null
      }
    }
  else return function.call(content)
  }

fun main() {
  val content = ChoirContent()
  println(callFunction(content, Method.GET, "/member/7", "body"))
  println(callFunction(content, Method.GET, "/member/7", ""))
  println(callFunction(content, Method.GET, "/member", "7"))
/*
  val server = WebServer(content, 4711)
  server.start()
 */
  }
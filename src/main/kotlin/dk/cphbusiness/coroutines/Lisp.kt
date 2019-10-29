package dk.cphbusiness.coroutines

class Lisp(val head: Int, val tail: Lisp?) {
    fun insert(index: Int, value: Int): Lisp {
      if (index == 0) return Lisp(value, this)
      else if (tail == null) return Lisp(head, Lisp(value, null))
      else return Lisp(head, tail.insert(index - 1, value))
      }

    fun print() {
        print("$head, ")
        if (tail == null) println(" null")
        else tail.print()
        }
    }

fun sum(list: Lisp?): Int =
    if (list == null) 0
    else list.head + sum(list.tail)



fun main() {
    var l = Lisp(7, Lisp(9, Lisp(13, null)))
    l.print()
    l = l.insert(300, 23)
    l.print()
    }
package dk.cphbusiness.lambdas

class Book(val title: String) {
    var subtitle: String? = null
    val lines = mutableListOf<String>()

    fun line(line: String) {
        lines.add(line)
        }

    fun print() {
        println("<h1>$title</h1>")
        println("<h2>$subtitle</h2>")
        println("<p>")
        lines.forEach { println(it) }
        println("</p>")
        }
    }

fun book(title: String, initialize: Book.() -> Unit = { }): Book {
    val book = Book(title)
    book.initialize()
    return book
    }


fun main() {
    book("War and peace") {
        subtitle = "Russian Romance"
        line("Once upon a time")
        line("in the wild west...")
        }.print()
    }
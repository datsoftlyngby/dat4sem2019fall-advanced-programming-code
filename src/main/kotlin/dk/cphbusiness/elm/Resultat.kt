package dk.cphbusiness.elm

sealed class Resultat {
  class Godt(val navn: String) : Resultat()
  class Skidt(val message: String) : Resultat()
  }

fun foo(i: Int): Resultat =
  if (i == 0) Resultat.Skidt("øv!")
  else Resultat.Godt("Kurt")

fun main() {
  val r = foo(7)
  when (r) {
    is Resultat.Godt -> println(r.navn)
    is Resultat.Skidt -> println(r.message)
    }
  }
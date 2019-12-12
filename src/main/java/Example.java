
public class Example {

  static Resultat foo(int i) {
    if (i == 0) return new Resultat.Skidt("øv");
    else return new Resultat.Godt("Kurt");
    }

  public static void main(String... args) {
    Resultat r = foo(7);
    if (r instanceof Resultat.Godt)
      System.out.println("Navnet er "+((Resultat.Godt) r).navn);
    else
      System.out.println("Det var "+((Resultat.Skidt) r).message);
    }

}

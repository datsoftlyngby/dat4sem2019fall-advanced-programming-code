public class Resultat {
  static class Godt extends Resultat {
    String navn;
    Godt(String n) { navn = n; }
    }
  static class Skidt extends Resultat {
    String message;
    Skidt(String m) { message = m; }
    }
  }

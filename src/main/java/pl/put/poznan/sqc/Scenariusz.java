package pl.put.poznan.sqc;

import java.util.List;

public class Scenariusz {
    String tytul;
    List<String> aktorzy;
    String aktorSystemowy;
    // Lista kroków w scenariuszu
    List<Krok> kroki;

    public List<String> getAktorzy() {
        return aktorzy;
    }

    public String getAktorSystemowy() {
        return aktorSystemowy;
    }

    public String getTytul() {
        return tytul;
    }

    public Scenariusz(String tytul, List<String> aktorzy, String aktorSystemowy, List<Krok> kroki) {
        this.tytul=tytul;
        this.aktorzy = aktorzy;
        this.aktorSystemowy = aktorSystemowy;
        this.kroki = kroki;
    }

    // Metoda pozwalająca wizytatorowi odwiedzić wszystkie kroki w scenariuszu
    public void akceptuj(Wizytator wizytator) {
        for (Krok krok : kroki) {
            krok.akceptuj(wizytator);
        }
    }
}
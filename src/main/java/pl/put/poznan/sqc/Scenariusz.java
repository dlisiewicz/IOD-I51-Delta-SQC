package pl.put.poznan.sqc;

import java.util.List;

/**
 *Klasa zawierajaca dane scenariusza
 */
public class Scenariusz {
    String tytul;
    List<String> aktorzy;
    String aktorSystemowy;
    // Lista krokow w scenariuszu
    List<Krok> kroki;

    /**
     *Zwraca liste aktorow
     * @return Lista aktorow
     */
    public List<String> getAktorzy() {
        return aktorzy;
    }

    /**
     *Zwraca aktora systemowego
     * @return Aktor systemowy
     */
    public String getAktorSystemowy() {
        return aktorSystemowy;
    }

    /**
     *Zwraca tytul scenariusza
     * @return Tytul
     */
    public String getTytul() {
        return tytul;
    }

    /**
     *Inicjuje scenariusz
     * @param tytul Tytul scenariusza
     * @param aktorzy Osoby zewnetrzne wykonujace podane kroki
     * @param aktorSystemowy System wewnetrzny wykonujacy podane kroki
     * @param kroki Kroki do wykonania w scenariuszu
     */
    public Scenariusz(String tytul, List<String> aktorzy, String aktorSystemowy, List<Krok> kroki) {
        this.tytul=tytul;
        this.aktorzy = aktorzy;
        this.aktorSystemowy = aktorSystemowy;
        this.kroki = kroki;
    }

    /**
     * Metoda pozwalajaca wizytatorowi odwiedzic wszystkie kroki w scenariuszu
     * @param wizytator Odwiedzajacy wizytator
     */
    public void akceptuj(Wizytator wizytator) {
        if(kroki!=null){
            for (Krok krok : kroki) {
                krok.akceptuj(wizytator);
            }
        }
    }
}
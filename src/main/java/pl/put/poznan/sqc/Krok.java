package pl.put.poznan.sqc;

import java.util.List;

/**
 *Klasa trzymajaca tresc kroku i informacje o podscenariuszach
 */
public class Krok {
    String tresc;
    public List<Krok> podKrok;

    /**
     *Inicjuje klase
     * @param tresc Tekst kroku
     * @param podKrok Lista krokow podscenariusza
     */
    public Krok(String tresc, List<Krok> podKrok) {
        this.tresc = tresc;
        this.podKrok = podKrok;
    }

    /**
     * Dodaje krok do listy podkroków na pozycji i
     * @param i index
     * @param krok krok
     */
    public void dodajPodKrok(int i, Krok krok){
        podKrok.add(i,krok);
    }

    /**
     *Zwraca tresc kroku
     * @return Tresc
     */
    public String getTresc() {
        return tresc;
    }

    /**
     *Funkcja pozwalajaca wizytatorowi odwiedzic ten krok
     * @param wizytator Odwiedzajacy wizytator
     */
    public void akceptuj(Wizytator wizytator) {
        wizytator.odwiedz(this);
    }
}
package pl.put.poznan.sqc.bezAktora;

import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.Wizytator;

import java.util.ArrayList;
import java.util.List;

/**
 *Klasa zbierajaca liste krokow, ktore nie zaczynaja sie od wykonania czynnosci przez aktora
 */
public class bezAktora implements Wizytator {
    public List<String> wynik = new ArrayList<>();
    boolean zawieraAktora = false;
    List<String> aktorzy;
    String aktorSystemowy;

    /**
     *Inicjuje klase
     * @param aktorzy Lista aktorow zewnetrznych scenariusza
     * @param aktorSystemowy Aktor systemowy scenariusza
     */
    public bezAktora(List<String> aktorzy, String aktorSystemowy) {
        this.aktorzy = aktorzy;
        this.aktorSystemowy = aktorSystemowy;
    }

    /**
     *dodaje krok do listy jezeli nie zawiera w tresci nazwy aktora
     * @param krok Krok scenariusza
     */
    @Override
    public void odwiedz(Krok krok) {
        for(String aktor : aktorzy){
            if (krok.getTresc().contains(aktor)){
                zawieraAktora = true;
                break;
            }
        }
        if(krok.getTresc().contains(aktorSystemowy)) zawieraAktora = true;

        if(!zawieraAktora) {
            wynik.add(krok.getTresc());
        }
        zawieraAktora = false;
        if (krok.podKrok != null) {
            for (Krok podKrok : krok.podKrok) {
                podKrok.akceptuj(this);
            }
        }
    }
    /**
     *Zwwraca liste aktorów
     * @return Lista aktrów w formacie string
     */
    public List<String> getAktorzy() {
        return aktorzy;
    }

    /**
     *Zwwraca liste krokow, ktore nie zaczynaja sie od wykonania czynnosci przez aktora
     * @return Lista krokow w formacie string
     */
    public List<String> getWynik() {
        return wynik;
    }
}

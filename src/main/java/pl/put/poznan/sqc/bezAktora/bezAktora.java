package pl.put.poznan.sqc.bezAktora;

import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.Wizytator;

import java.util.ArrayList;
import java.util.List;

public class bezAktora implements Wizytator {
    public List<String> wynik = new ArrayList<>();
    boolean zawieraAktora = false;
    List<String> aktorzy;
    String aktorSystemowy;
    public bezAktora(List<String> aktorzy, String aktorSystemowy) {
        this.aktorzy = aktorzy;
        this.aktorSystemowy = aktorSystemowy;}
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
    public List<String> getWynik() {
        return wynik;
    }
}

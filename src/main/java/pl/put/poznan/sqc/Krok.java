package pl.put.poznan.sqc;

import java.util.List;
public class Krok {
    String tresc;
    public List<Krok> podKrok;

    public Krok(String tresc, List<Krok> podKrok) {
        this.tresc = tresc;
        this.podKrok = podKrok;
    }

    public String getTresc() {
        return tresc;
    }
    public void akceptuj(Wizytator wizytator) {
        wizytator.odwiedz(this);
    }
}
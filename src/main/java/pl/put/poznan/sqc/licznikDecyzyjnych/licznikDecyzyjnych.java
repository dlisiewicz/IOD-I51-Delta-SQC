package pl.put.poznan.sqc.licznikDecyzyjnych;

import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Wizytator;

public class licznikDecyzyjnych implements Wizytator {
    private int count = 0;

    @Override
    public void odwiedz(Krok krok) {
        if (krok.getTresc().contains("IF") ||
                krok.getTresc().contains("ELSE") ||
                krok.getTresc().contains("FOR EACH")) {
            count++;
        }
        if (krok.podKrok != null) {
            for (Krok podKrok : krok.podKrok) {
                podKrok.akceptuj(this);
            }
        }
    }

    public int getCount() {
        return count;
    }
}

package pl.put.poznan.sqc.licznikDecyzyjnych;

import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Wizytator;

/**
 *Klasa zliczajaca kroki scenariusza zawierajace decyzje warunkowe
 */
public class licznikDecyzyjnych implements Wizytator {
    private int count = 0;

    /**
     *dodaje krok do licznika jezeli zawiera slowo kluczowe, oraz wywoluje sie dla wszystkich krokow podscenariusza, jezeli taki istnieje.
     * @param krok Krok scenariusza
     */
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

    /**
     *Zwraca liczbe krokow zawierajacych decyzje warunkowe
     * @return Liczba krokow
     */
    public int getCount() {
        return count;
    }
}

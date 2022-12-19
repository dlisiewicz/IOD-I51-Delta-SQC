package pl.put.poznan.sqc.licznikKrokow;

import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Wizytator;

/**
 *Klasa zliczajaca liste wszystkich krokow w scenariuszu i jego podscenariuszach
 */
public class licznikKrokow implements Wizytator {
    // Licznik krokow
    private int count = 0;

    /**
     *dodaje krok do licznika, oraz wywoluje sie dla wszystkich krokow podscenariusza, jezeli taki istnieje.
     * @param krok Krok scenariusza
     */
    // Metoda odwiedzajaca krok
    @Override
    public void odwiedz(Krok krok) {
        // Zwieksz licznik krokow
        count++;

        // Odwiedz pod-kroki, jesli istnieja
        if (krok.podKrok != null) {
            for (Krok podKrok : krok.podKrok) {
                podKrok.akceptuj(this);
            }
        }
    }

    /**
     *Zwraca liczbe krokow w scenariuszu i jego podscenariuszach
     * @return liczba krokow
     */
    // Zwroc liczbe krokow
    public int getCount() {
        return count;
    }
}
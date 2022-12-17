package pl.put.poznan.sqc.licznikKrokow;

import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Wizytator;

public class licznikKrokow implements Wizytator {
    // Licznik kroków
    private int count = 0;

    // Metoda odwiedzająca krok
    @Override
    public void odwiedz(Krok krok) {
        // Zwiększ licznik kroków
        count++;

        // Odwiedź pod-kroki, jeśli istnieją
        if (krok.podKrok != null) {
            for (Krok podKrok : krok.podKrok) {
                podKrok.akceptuj(this);
            }
        }
    }
    // Zwróć liczbę kroków
    public int getCount() {
        return count;
    }
}
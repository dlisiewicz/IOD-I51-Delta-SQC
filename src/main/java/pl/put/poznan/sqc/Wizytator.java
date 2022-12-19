package pl.put.poznan.sqc;

import pl.put.poznan.sqc.Krok;

/**
 *Wzorzec projektowy oddzielajacy algorytm od struktury, na ktorej operuje
 */
public interface Wizytator {
    /**
     *Procedura odwiedzajaca krok scenariusza
     * @param krok Krok scenariusza
     */
    void odwiedz(Krok krok);
}

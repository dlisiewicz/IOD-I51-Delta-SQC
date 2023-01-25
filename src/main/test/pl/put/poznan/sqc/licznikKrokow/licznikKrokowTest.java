package pl.put.poznan.sqc.licznikKrokow;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.licznikDecyzyjnych.licznikDecyzyjnych;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class licznikKrokowTest {
    @Test
    public void testAllSteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok step3_1 = new Krok("ELSE Krok 3-1", null);
        Krok step3_2 = new Krok("Dozorca Pod-krok 3-2", null);
        Krok step3 = new Krok("IF Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        licznikKrokow licznikKrokow = new licznikKrokow();
        scenariusz.akceptuj(licznikKrokow);
        int count = licznikKrokow.getCount();

        assertEquals(5, count);
    }
    @Test
    public void testNoSubSteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok step3 = new Krok("IF Krok 3", null);

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        licznikKrokow licznikKrokow = new licznikKrokow();
        scenariusz.akceptuj(licznikKrokow);
        int count = licznikKrokow.getCount();

        assertEquals(3, count);
    }

    @Test
    public void testSubSteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("System Krok 2", null);
        Krok step3_1 = new Krok("FOR EACH Krok 3-1", null);
        Krok step3_2_1_1 = new Krok("ELSE Pod-krok 3-2", null);
        Krok step3_2_1 = new Krok("Dozorca Pod-krok 3-2", Arrays.asList(step3_2_1_1));
        Krok step3_2 = new Krok("IF Pod-krok 3-2", Arrays.asList(step3_2_1));
        Krok step3 = new Krok("System Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        licznikKrokow licznikKrokow = new licznikKrokow();
        scenariusz.akceptuj(licznikKrokow);
        int count = licznikKrokow.getCount();

        assertEquals(7, count);
    }

    @Test
    public void testOneStep() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1));
        licznikKrokow licznikKrokow = new licznikKrokow();
        scenariusz.akceptuj(licznikKrokow);
        int count = licznikKrokow.getCount();

        assertEquals(1, count);
    }

    @Test
    public void testOlySubSteps() {
        Krok step3_1_2 = new Krok("Krok 3-1", null);
        Krok step3_1_1 = new Krok("FOR Krok 3-1", null);
        Krok step3_1 = new Krok("FOR EACH Krok 3-1", Arrays.asList(step3_1_2, step3_1_1));
        Krok step3_2_1_1_1 = new Krok("ELSE Pod-krok 3-2", null);
        Krok step3_2_1_1 = new Krok("ELSE Pod-krok 3-2", Arrays.asList(step3_2_1_1_1));
        Krok step3_2_1 = new Krok("Dozorca Pod-krok 3-2", Arrays.asList(step3_2_1_1));
        Krok step3_2 = new Krok("IF Pod-krok 3-2", Arrays.asList(step3_2_1));
        Krok step3 = new Krok("System Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step3));
        licznikKrokow licznikKrokow = new licznikKrokow();
        scenariusz.akceptuj(licznikKrokow);
        int count = licznikKrokow.getCount();

        assertEquals(8, count);
    }

}
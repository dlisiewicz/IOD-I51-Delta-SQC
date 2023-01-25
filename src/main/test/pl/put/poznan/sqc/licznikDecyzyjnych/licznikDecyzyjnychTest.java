package pl.put.poznan.sqc.licznikDecyzyjnych;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sqc.Krok;
import pl.put.poznan.sqc.Scenariusz;
import pl.put.poznan.sqc.bezAktora.bezAktora;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class licznikDecyzyjnychTest {
    @Test
    public void testAllSteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok step3_1 = new Krok("ELSE Krok 3-1", null);
        Krok step3_2 = new Krok("Dozorca Pod-krok 3-2", null);
        Krok step3 = new Krok("IF Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        licznikDecyzyjnych licznikDecyzyjnych = new licznikDecyzyjnych();
        scenariusz.akceptuj(licznikDecyzyjnych);
        int count = licznikDecyzyjnych.getCount();

        assertEquals(3, count);
    }
    @Test
    public void testAllDec() {
        Krok step1 = new Krok("ELSE Krok 1", null);
        Krok step2 = new Krok("ELSE Krok 2", null);
        Krok step3_1 = new Krok("ELSE Krok 3-1", null);
        Krok step3_2 = new Krok("FOR EACH Pod-krok 3-2", null);
        Krok step3 = new Krok("IF Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        licznikDecyzyjnych licznikDecyzyjnych = new licznikDecyzyjnych();
        scenariusz.akceptuj(licznikDecyzyjnych);
        int count = licznikDecyzyjnych.getCount();

        assertEquals(5, count);
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
        licznikDecyzyjnych licznikDecyzyjnych = new licznikDecyzyjnych();
        scenariusz.akceptuj(licznikDecyzyjnych);
        int count = licznikDecyzyjnych.getCount();

        assertEquals(3, count);
    }
    @Test
    public void testOneEmpty() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1));
        licznikDecyzyjnych licznikDecyzyjnych = new licznikDecyzyjnych();
        scenariusz.akceptuj(licznikDecyzyjnych);
        int count = licznikDecyzyjnych.getCount();

        assertEquals(0, count);
    }

    @Test
    public void testDecOnlySubsteps() {
        Krok step1 = new Krok("Bibliotekarz Krok 1", null);
        Krok step2 = new Krok("System Krok 2", null);
        Krok step3_1 = new Krok("Krok 3-1", null);
        Krok step3_2_1_1 = new Krok("ELSE Pod-krok 3-2", null);
        Krok step3_2_1 = new Krok("Dozorca Pod-krok 3-2", Arrays.asList(step3_2_1_1));
        Krok step3_2 = new Krok("Pod-krok 3-2", Arrays.asList(step3_2_1));
        Krok step3 = new Krok("System Krok 3", Arrays.asList(step3_1,step3_2));

        Scenariusz scenariusz = new Scenariusz("Scenariusz",Arrays.asList("Bibliotekarz","Dozorca"), "System", Arrays.asList(step1, step2, step3));
        licznikDecyzyjnych licznikDecyzyjnych = new licznikDecyzyjnych();
        scenariusz.akceptuj(licznikDecyzyjnych);
        int count = licznikDecyzyjnych.getCount();

        assertEquals(1, count);
    }

}